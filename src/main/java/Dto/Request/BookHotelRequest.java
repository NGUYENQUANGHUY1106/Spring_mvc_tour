package Dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Date;



@Getter
public class BookHotelRequest {
    private Long idHotel;


    private Long idUser;
    private int totalPrice;
    private int countRoom;
    private Date bookStart;
    private Date bookEnd;

    private String bedType;
    private String roomType;


    public Long getIdHotel() {
        return idHotel;
    }

    public Long getIdUser() {
        return idUser;
    }

    public int getCountRoom() {
        return countRoom;
    }
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkinTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkoutTime;

    public LocalTime getCheckinTime() {
        return checkinTime;
    }

    public LocalTime getCheckoutTime() {
        return checkoutTime;
    }

}
