package Repository;

import Entity.FavoriteHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteHotelRepository extends JpaRepository<FavoriteHotel, Long> {
    Optional<FavoriteHotel> findByUserIdAndHotelId(Long userId, Long hotelId);

    List<FavoriteHotel> findByUserId(Long userId);

    long countByUserId(Long userId); // ✅ đảm bảo dòng này có




}
