package Dto.Request;


import lombok.Getter;

@Getter
public class HotelRequest {
    private String username;


    private int room ;

    private String img;
    private UserRequest user;
    private AddressRequest address;
    private int price;

    public void setRoom(int room) {
        this.room = room;
    }

    public int getRoom() {
        return room;
    }
}
