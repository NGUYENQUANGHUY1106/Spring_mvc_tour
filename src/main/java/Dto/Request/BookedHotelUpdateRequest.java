package Dto.Request;


import lombok.Getter;

import java.util.Date;

@Getter
public class BookedHotelUpdateRequest {
    private Long id;
    private int totalPrice;
    private int countRoom;
    private Date bookStart;
    private Date bookEnd;
}
