package Repository;

import Entity.AddressHotel;
import Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressHotelRepository extends JpaRepository<AddressHotel, Long> {
}
