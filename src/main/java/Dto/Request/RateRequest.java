package Dto.Request;

import lombok.Getter;

@Getter
public class RateRequest {
    private Long idUser;
    private Long idHotel;
    private float rateStar;
    private String comment;
}
