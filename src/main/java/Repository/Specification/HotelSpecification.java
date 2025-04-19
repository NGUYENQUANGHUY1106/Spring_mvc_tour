package Repository.Specification;

import Entity.AddressHotel;
import Entity.Hotel;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class HotelSpecification {
    public static Specification<Hotel> hasProvince(String province) {
        return (root, query, cb) -> {
            Join<Hotel, AddressHotel> addressJoin = root.join("addressHotel"); // Tên field trong Hotel
            return cb.equal(cb.lower(addressJoin.get("province")), province.toLowerCase());
        };
    }

    public static Specification<Hotel> hasHotelName(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}
