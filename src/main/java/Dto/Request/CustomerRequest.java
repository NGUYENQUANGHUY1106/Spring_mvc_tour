package Dto.Request;

import lombok.Getter;

@Getter
public class CustomerRequest {
    private UserRequest user;
    private String phone;
    private String username;
}
