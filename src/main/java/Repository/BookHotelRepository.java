package Repository;

import Entity.BookHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookHotelRepository extends JpaRepository<BookHotel, Long> {
    List<BookHotel> findAllByCustomer_Id(Long idCustomer);
    List<BookHotel> findAllByHotel_Id(Long idHotel);
    @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM BookHotel b WHERE b.hotel.id = :hotelId AND b.statusBook = 'COMFIRMED'")
    double getTotalRevenueByHotelId(@Param("hotelId") Long hotelId);
}
