package Dto.Request;

import java.util.Date;

public class BookedHotelUpdateRequest {
    private Long id;
    private int totalPrice;
    private int countRoom;
    private Date bookStart;
    private Date bookEnd;
    private String checkinTime;
    private String checkoutTime;

    private String bedType;
    private String roomType;

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getCountRoom() {
        return countRoom;
    }

    public Date getBookStart() {
        return bookStart;
    }

    public Date getBookEnd() {
        return bookEnd;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCountRoom(int countRoom) {
        this.countRoom = countRoom;
    }

    public void setBookStart(Date bookStart) {
        this.bookStart = bookStart;
    }

    public void setBookEnd(Date bookEnd) {
        this.bookEnd = bookEnd;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }


}
