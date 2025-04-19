package Dto.Response;


import Dto.Request.CustomerRequest;
import Dto.Request.UserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    private UserResponse user;
    private String phone;
    private String username;
}
