package Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel extends AbstractEntity<Long> {

    private String name;

    @OneToOne
    private AddressHotel addressHotel;

    @OneToOne
    private UserEntity user;

    private String img;
    private int price;
    private int room;

    @OneToMany(mappedBy = "hotel")
    private List<BookHotel> bookHotels;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Rate> rates;

    // ✅ Getter/Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressHotel getAddressHotel() {
        return addressHotel;
    }

    public void setAddressHotel(AddressHotel addressHotel) {
        this.addressHotel = addressHotel;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public List<BookHotel> getBookHotels() {
        return bookHotels;
    }

    public void setBookHotels(List<BookHotel> bookHotels) {
        this.bookHotels = bookHotels;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    // ✅ Trả về địa chỉ dạng chuỗi
    public String getAddress() {
        return (addressHotel != null) ? addressHotel.getAddress() : "Địa chỉ chưa cập nhật";
    }
}
