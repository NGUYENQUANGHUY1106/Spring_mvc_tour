package Service;

import Entity.FavoriteHotel;
import Entity.Hotel;
import Repository.FavoriteHotelRepository;
import Repository.HotelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavoriteHotelService {

    @Autowired
    private FavoriteHotelRepository favoriteHotelRepo;

    @Autowired
    private HotelRepository hotelRepo;

    public void toggleFavorite(Long userId, Long hotelId) {
        System.out.println(" Yêu cầu lưu yêu thích: userId = " + userId + ", hotelId = " + hotelId);
        Optional<FavoriteHotel> existing = favoriteHotelRepo.findByUserIdAndHotelId(userId, hotelId);
        if (existing.isPresent()) {
            favoriteHotelRepo.delete(existing.get());
            System.out.println(" Đã bỏ yêu thích khách sạn hotelId = " + hotelId);
        } else {
            Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(() -> new RuntimeException(" Không tìm thấy khách sạn"));
            FavoriteHotel fav = new FavoriteHotel();
            fav.setUserId(userId);
            fav.setHotelId(hotelId);
            fav.setHotelName(hotel.getName());
            fav.setHotelImage(hotel.getImg());
            fav.setHotelRating(4.5); // mặc định
            fav.setHotelPrice((double) hotel.getPrice());
            fav.setHotelAddress(hotel.getAddress());
            fav.setAvailableRooms(hotel.getRoom());

            favoriteHotelRepo.save(fav);
            System.out.println("✅ Đã lưu khách sạn yêu thích vào CSDL!");
        }
    }

    public List<FavoriteHotel> getFavoritesByUser(Long userId) {
        return favoriteHotelRepo.findByUserId(userId);
    }

}
