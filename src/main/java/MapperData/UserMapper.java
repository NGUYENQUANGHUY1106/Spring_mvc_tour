package MapperData;

import Dto.Request.UserRequest;
import Dto.Response.UserResponse;
import Entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponse toUserResponse(UserEntity user);
    UserEntity toUserEntity(UserRequest userRequest);
}
