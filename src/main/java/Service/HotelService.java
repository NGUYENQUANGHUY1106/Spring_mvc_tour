package Service;

import Dto.Request.HotelRequest;
import Dto.Request.SearchRequest;
import Dto.Response.HotelCustomerGetResponse;
import Dto.Response.HotelResponse;
import Entity.AddressHotel;
import Entity.Hotel;
import Entity.UserEntity;
import Exception1.ResourceNotFoundException;
import MapperData.HotelMapper;
import Repository.BookHotelRepository;
import Repository.HotelRepository;
import Repository.Specification.HotelSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressHotelService addressHotelService;

    @Autowired
    private BookHotelRepository bookHotelRepository;

    @Transactional
    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // ✅ Thêm khách sạn mới
    public HotelResponse add(HotelRequest hotelRequest) {
        Hotel hotel = hotelMapper.toHotel(hotelRequest);
        hotel.setRoom(hotelRequest.getRoom());

        UserEntity userCreated = userService.addUserHotel(hotel.getUser());
        AddressHotel addressHotelCreated = addressHotelService.add(hotel.getAddressHotel());

        hotel.setAddressHotel(addressHotelCreated);
        hotel.setUser(userCreated);

        return hotelMapper.toHotelResponse(hotelRepository.save(hotel));
    }

    // ✅ Lấy danh sách tất cả khách sạn
    public List<HotelCustomerGetResponse> getAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(hotelMapper::toHotelCustomerGetResponse)
                .collect(Collectors.toList());
    }

    // ✅ Lấy chi tiết khách sạn theo ID
    public HotelResponse getHotelResponseById(Long idHotel) {
        Hotel hotel = hotelRepository.findById(idHotel)
                .orElseThrow(() -> {
                    System.out.println("Không tìm thấy khách sạn với id = " + idHotel);
                    return new ResourceNotFoundException("Không tìm thấy khách sạn với id = " + idHotel);
                });
        System.out.println("Tìm thấy khách sạn: " + hotel.getName() + ", room = " + hotel.getRoom());
        return hotelMapper.toHotelResponse(hotel);
    }

    // ✅ Lấy entity nội bộ
    public Hotel getHotelEntityById(Long idHotel) {
        return hotelRepository.findById(idHotel)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách sạn với id = " + idHotel));
    }

    // ✅ Lấy khách sạn theo userId
    public Hotel getHotelByUserId(Long idUser) {
        return hotelRepository.findByUser_Id(idUser);
    }

    // ✅ Tìm kiếm khách sạn
    public List<HotelResponse> search(SearchRequest searchRequest) {
        Specification<Hotel> spec = Specification.where(null);

        if (searchRequest.getNameHotel() != null && !searchRequest.getNameHotel().isEmpty()) {
            spec = spec.and(HotelSpecification.hasHotelName(searchRequest.getNameHotel()));
        }

        if (searchRequest.getProvince() != null && !searchRequest.getProvince().isEmpty()) {
            spec = spec.and(HotelSpecification.hasProvince(searchRequest.getProvince()));
        }

        List<Hotel> hotels = hotelRepository.findAll(spec);

        return hotels.stream()
                .map(hotelMapper::toHotelResponse)
                .collect(Collectors.toList());
    }

    // ✅ Lấy doanh thu theo userId
    public double getDoanhThu(Long idUser) {
        try {
            Hotel hotel = hotelRepository.findByUser_Id(idUser);
            return bookHotelRepository.getTotalRevenueByHotelId(hotel.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    // ✅ Lấy tổng số phòng trống theo userId
    public int getAvailableRoomByUserId(Long userId) {
        List<Hotel> hotels = hotelRepository.findAllByUser_Id(userId); // Gọi qua biến injected
        return hotels.stream()
                .mapToInt(Hotel::getRoom)
                .sum();
    }

}
