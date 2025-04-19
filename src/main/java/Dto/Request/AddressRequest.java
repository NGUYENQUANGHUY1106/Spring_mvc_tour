package Dto.Request;


import lombok.Getter;

@Getter
public class AddressRequest {
    private String province;
    private String district;
    private String town;
    private String detailPlace;
}
