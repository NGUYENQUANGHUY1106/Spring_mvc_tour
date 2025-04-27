package MapperData;

import Dto.Request.HotelRequest;
import Dto.Response.HotelCustomerGetResponse;
import Dto.Response.HotelResponse;
import Entity.AddressHotel;
import Entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserMapper.class,AddressMappper.class})
public interface HotelMapper {
    @Mapping(source = "address", target = "addressHotel")
    @Mapping(source = "username", target = "name")
    @Mapping(source = "room", target = "room")
    Hotel toHotel(HotelRequest hotelRequest);
    @Mapping(source = "name", target = "username")
    @Mapping(source = "addressHotel", target = "address")
    @Mapping(source = "room", target = "room")
    HotelResponse toHotelResponse(Hotel hotel);

    @Mapping(source = "addressHotel", target = "address")
    @Mapping(source = "name", target = "username")
    @Mapping(source = "room", target = "room")
    HotelCustomerGetResponse toHotelCustomerGetResponse(Hotel hotel);
}
