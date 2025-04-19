package MapperData;


import Dto.Request.AddressRequest;
import Dto.Response.AddressResponse;
import Entity.AddressHotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMappper {
    @Mapping(source = "detailPlace", target = "detail")
    AddressHotel toAddressHotel(AddressRequest addressRequest);
    @Mapping(source = "detail", target = "detailPlace")
    AddressResponse toAddressResponse(AddressHotel addressHotel);
}
