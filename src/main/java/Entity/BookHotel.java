package Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_hotel")
public class BookHotel extends AbstractEntity<Long>{
    private Date bookStart;
    private Date bookEnd;
    private String statusBook;
    private int totalPrice;
    private int countRoom;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Hotel hotel;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getStatusBook() {
        return statusBook;
    }

    public void setStatusBook(String statusBook) {
        this.statusBook = statusBook;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public Hotel getHotel() { return hotel; }
    public int getCountRoom() {
        return countRoom;
    }
    @Column(name = "checkin_time")
    private LocalTime checkinTime;

    @Column(name = "checkout_time")
    private LocalTime checkoutTime;

    public LocalTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }


}
