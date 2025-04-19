package Dto.Response;

import Entity.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RateResponse {
    private CustomerResponse customer;
    private String comment;
    private float rateStar;
    private Date createdAt;
}
