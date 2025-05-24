package Repository;

import Entity.BookHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookHotelRepository extends JpaRepository<BookHotel, Long> {
    List<BookHotel> findAllByCustomer_Id(Long idCustomer);
    List<BookHotel> findAllByHotel_Id(Long idHotel);
    List<BookHotel> findByHotel_User_IdAndStatusBook(Long userId, String statusBook);


    @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM BookHotel b WHERE b.hotel.id = :hotelId AND b.statusBook = 'CONFIRMED'")
    double getTotalRevenueByHotelId(@Param("hotelId") Long hotelId);

    // ✅ Fix lỗi bằng cách dùng @Query rõ ràng
    @Query("SELECT COUNT(b) FROM BookHotel b WHERE b.hotel.user.id = :userId AND b.statusBook = :status")
    int countBookingsByUserAndStatus(@Param("userId") Long userId, @Param("status") String status);
}
