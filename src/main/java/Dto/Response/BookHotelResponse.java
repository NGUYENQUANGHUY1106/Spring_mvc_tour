package Dto.Response;


import Entity.Customer;
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
public class BookHotelResponse {
    private Long id;
    private Date bookStart;
    private Date bookEnd;
    private String statusBook;
    private int totalPrice;
    private int countRoom;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String modifiedBy;

    private HotelResponse hotel;
}
