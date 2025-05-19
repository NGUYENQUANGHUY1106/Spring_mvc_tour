package Controller10;

import Dto.Request.BookHotelRequest;
import Dto.Request.BookedHotelUpdateRequest;
import Dto.Response.BookHotelResponse;
import Entity.BookHotel;
import Entity.Customer;
import Repository.BookHotelRepository;
import Repository.CustomerRepository;
import Service.BookHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book_hotel")
public class BookHotelController {

    @Autowired
    private BookHotelRepository bookHotelRepository;

    @Autowired
    private BookHotelService bookHotelService;

    @Autowired
    private CustomerRepository customerRepository; // ✅ thêm dòng này

    @PostMapping()
    public BookHotelResponse bookHotel(@RequestBody BookHotelRequest bookHotelRequest) {
        return bookHotelService.bookHotel(bookHotelRequest);
    }

    @GetMapping("/{idUser}")
    public List<BookHotelResponse> getBookHotelByIdUser(@PathVariable Long idUser) {
        return bookHotelService.getBookHotelByIdUser(idUser);
    }

    @PutMapping("/{idBookHotel}/{status}")
    public BookHotelResponse updateStatus(@PathVariable Long idBookHotel, @PathVariable String status) {
        return bookHotelService.updateStatusBooked(idBookHotel, status);
    }

    @PutMapping()
    public BookHotelResponse updateBooked(@RequestBody BookedHotelUpdateRequest bookedHotelUpdateRequest) {
        return bookHotelService.updateBooked(bookedHotelUpdateRequest);
    }

    @PutMapping("/checkout/{idBookHotel}")
    public BookHotelResponse checkout(@PathVariable Long idBookHotel) {
        return bookHotelService.checkoutBookHotel(idBookHotel);
    }

    // ✅ Sửa lại hàm đếm đúng theo customerId
    @GetMapping("/booking-counts/{userId}")
    public ResponseEntity<Map<String, Long>> getBookingCounts(@PathVariable Long userId) {
        Customer customer = customerRepository.findByUserId(userId); // cần thêm phương thức này trong repository
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        Long customerId = customer.getId();

        Map<String, Long> result = new HashMap<>();
        result.put("COMFIRMED", bookHotelRepository.countByCustomerIdAndStatusBook(customerId, "COMFIRMED"));
        result.put("WAIT", bookHotelRepository.countByCustomerIdAndStatusBook(customerId, "WAIT"));
        result.put("CHECKOUT", bookHotelRepository.countByCustomerIdAndStatusBook(customerId, "CHECKOUT"));

        return ResponseEntity.ok(result);
    }
}
