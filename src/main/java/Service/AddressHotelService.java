package Service;

import Entity.AddressHotel;
import Repository.AddressHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressHotelService {
    @Autowired
    private AddressHotelRepository addressHotelRepository;

    public AddressHotel add(AddressHotel addressHotel) {
        return addressHotelRepository.save(addressHotel);
    }
}
