package Controller10;

import Dto.Request.BookHotelRequest;
import Dto.Request.BookedHotelUpdateRequest;
import Dto.Response.BookHotelResponse;
import Entity.BookHotel;
import Repository.BookHotelRepository;
import Service.BookHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book_hotel")
public class BookHotelController {

    @Autowired
    private BookHotelService bookHotelService;

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
        return bookHotelService.updateStatusBooked(idBookHotel,status);
    }

    @PutMapping()
    public BookHotelResponse updateBooked( @RequestBody BookedHotelUpdateRequest bookedHotelUpdateRequest) {
        return bookHotelService.updateBooked(bookedHotelUpdateRequest);
    }

}
