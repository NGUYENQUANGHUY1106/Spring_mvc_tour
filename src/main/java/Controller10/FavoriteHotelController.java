package Controller10;

import Entity.FavoriteHotel;
import Repository.FavoriteHotelRepository;
import Service.FavoriteHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@CrossOrigin(origins = "*")
public class FavoriteHotelController {

    @Autowired
    private FavoriteHotelService service;
    @Autowired
    private FavoriteHotelRepository favoriteHotelRepository ;

    @PostMapping("/toggle/{userId}/{hotelId}")
    public ResponseEntity<?> toggleFavorite(@PathVariable Long userId, @PathVariable Long hotelId) {
        System.out.println(" Yêu cầu lưu yêu thích: userId = " + userId + ", hotelId = " + hotelId);
        service.toggleFavorite(userId, hotelId);
        System.out.println(" Controller đã nhận request: userId=" + userId + ", hotelId=" + hotelId);

        return ResponseEntity.ok("Toggled");

    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteHotel>> getFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getFavoritesByUser(userId));
    }

    @GetMapping("/count/{userId}") // ✅ THÊM API NÀY
    public ResponseEntity<Long> getFavoriteCount(@PathVariable Long userId) {
        long count = favoriteHotelRepository.countByUserId(userId);
        return ResponseEntity.ok(count);
    }





}
