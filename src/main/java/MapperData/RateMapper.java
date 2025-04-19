package MapperData;


import Dto.Request.RateRequest;
import Dto.Response.RateResponse;
import Entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {CustomerMapper.class})
public interface RateMapper {
    Rate toRate(RateRequest rateRequest);
    RateResponse toRateResponse(Rate rate);
}
