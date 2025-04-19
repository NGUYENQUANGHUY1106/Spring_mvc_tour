package MapperData;

import Dto.Request.BookHotelRequest;
import Dto.Request.BookedHotelUpdateRequest;
import Dto.Response.BookHotelResponse;
import Entity.AddressHotel;
import Entity.BookHotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {HotelMapper.class, AddressHotel.class})
public interface BookHotelMapper {
    BookHotel toBookHotel(BookHotelRequest bookHotelRequest);
    BookHotelResponse toBookHotelResponse(BookHotel bookHotel);
}
