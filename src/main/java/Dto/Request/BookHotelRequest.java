package Dto.Request;

import lombok.Getter;

import java.util.Date;

@Getter
public class BookHotelRequest {
    private Long idHotel;


    private Long idUser;
    private int totalPrice;
    private int countRoom;
    private Date bookStart;
    private Date bookEnd;

    public Long getIdHotel() {
        return idHotel;
    }

    public Long getIdUser() {
        return idUser;
    }

    public int getCountRoom() {
        return countRoom;
    }
}
