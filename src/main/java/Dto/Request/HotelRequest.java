package Dto.Request;


import lombok.Getter;

@Getter
public class HotelRequest {
    private String username;
    private String img;
    private UserRequest user;
    private AddressRequest address;
    private int price;
}
