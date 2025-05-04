package Service;

import Dto.Request.BookHotelRequest;
import Dto.Request.BookedHotelUpdateRequest;
import Dto.Response.BookHotelResponse;
import Entity.BookHotel;
import Entity.Customer;
import Entity.Hotel;
import Enum1.EnumRole;
import Enum1.EnumStatusBook;
import Exception1.ResourceNotFoundException;
import MapperData.BookHotelMapper;
import MapperData.HotelMapper;
import Repository.BookHotelRepository;
import Repository.HotelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookHotelService {
    @Autowired
    private BookHotelRepository bookHotelRepository;
    @Autowired
    private BookHotelMapper bookHotelMapper;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private HotelRepository hotelRepository;

    public BookHotelResponse bookHotel(BookHotelRequest bookHotelRequest) {
        BookHotel bookHotel = bookHotelMapper.toBookHotel(bookHotelRequest);
        Hotel hotel = hotelService.getHotelEntityById(bookHotelRequest.getIdHotel());
        Customer customer = customerService.getCustomerByIdUser(bookHotelRequest.getIdUser());

        // 🛠 Bước kiểm tra và trừ số phòng
        int currentRoom = hotel.getRoom();
        if (currentRoom < bookHotelRequest.getCountRoom()) {
            throw new IllegalArgumentException("Không đủ phòng trống để đặt!");
        }
        hotel.setRoom(currentRoom - bookHotelRequest.getCountRoom());
        hotelService.updateHotel(hotel);

        // Set các thông tin booking
        bookHotel.setCustomer(customer);
        bookHotel.setHotel(hotel);
        bookHotel.setStatusBook(EnumStatusBook.WAIT.name());

        return bookHotelMapper.toBookHotelResponse(bookHotelRepository.save(bookHotel));
    }

    @Transactional
    public BookHotelResponse checkoutBookHotel(Long idBookHotel) {
        BookHotel bookHotel = bookHotelRepository.findById(idBookHotel)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy booking với id = " + idBookHotel));

        if (!EnumStatusBook.CONFIRMED.name().equals(bookHotel.getStatusBook())) {
            throw new IllegalStateException("Chỉ có thể trả phòng khi đã xác nhận đặt phòng!");
        }

        // 1. Đổi trạng thái booking thành CHECKOUT
        bookHotel.setStatusBook(EnumStatusBook.CHECKOUT.name());
        bookHotelRepository.save(bookHotel);

        // 2. Cộng lại số phòng cho khách sạn
        Hotel hotel = bookHotel.getHotel();
        hotel.setRoom(hotel.getRoom() + bookHotel.getCountRoom());
        hotelRepository.save(hotel);

        return bookHotelMapper.toBookHotelResponse(bookHotel);
    }


    public List<BookHotelResponse> getBookHotelByIdUser(Long idUser) {
        String role = getCurrentUserRole();
        List<BookHotel> bookings = new ArrayList<>();
        if(role.equals(EnumRole.CUSTOMER.name())) {
            Customer customer = customerService.getCustomerByIdUser(idUser);
            bookings = bookHotelRepository.findAllByCustomer_Id(customer.getId());
        }else if(role.equals(EnumRole.HOTEL.name())) {
            Hotel hotel = hotelService.getHotelByUserId(idUser);
            bookings = bookHotelRepository.findAllByHotel_Id(hotel.getId());

        }
        return bookings.stream().map(bookHotel -> bookHotelMapper.toBookHotelResponse(bookHotel)).collect(Collectors.toList());
    }



    public BookHotelResponse updateStatusBooked(Long idBooked, String status) {
        BookHotel bookHotel = bookHotelRepository.findById(idBooked).get();
        bookHotel.setStatusBook(status);

        if (EnumStatusBook.CHECKOUT.name().equals(status)) {
            Hotel hotel = bookHotel.getHotel();
            hotel.setRoom(hotel.getRoom() + bookHotel.getCountRoom());
            hotelRepository.save(hotel);
        }

        return bookHotelMapper.toBookHotelResponse(bookHotelRepository.save(bookHotel));
    }


    public BookHotelResponse updateBooked(BookedHotelUpdateRequest bookedHotelUpdateRequest) {
        BookHotel bookHotel = bookHotelRepository.findById(bookedHotelUpdateRequest.getId()).get();
        bookHotel.setBookStart(bookedHotelUpdateRequest.getBookStart());
        bookHotel.setBookEnd(bookedHotelUpdateRequest.getBookEnd());
        bookHotel.setTotalPrice(bookedHotelUpdateRequest.getTotalPrice());
        bookHotel.setCountRoom(bookedHotelUpdateRequest.getCountRoom());
        return bookHotelMapper.toBookHotelResponse(bookHotelRepository.save(bookHotel));
    }
    private String getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // lấy role dạng "ROLE_CUSTOMER"
                .findFirst()
                .orElse("ROLE_UNKNOWN");
    }
}
