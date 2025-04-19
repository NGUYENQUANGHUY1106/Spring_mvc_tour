package Dto.Response;


import Entity.AddressHotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelCustomerGetResponse {
    private Long id;
    private AddressResponse address;
    private String username;
    private int price;
    private String img;
    private double avgRate;
}
