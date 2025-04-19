package Dto.Request;

import Entity.AddressHotel;
import lombok.Getter;

@Getter
public class UserRequest {
    private String email;
    private String password;
}
