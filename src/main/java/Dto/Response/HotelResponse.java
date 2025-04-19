package Dto.Response;

import Dto.Request.UserRequest;
import Entity.AddressHotel;
import Entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    private Long id;
    private String username;
    private UserResponse user;
    private AddressResponse address;
    private int price;
    private Date createdAt;
    private String img;
}
