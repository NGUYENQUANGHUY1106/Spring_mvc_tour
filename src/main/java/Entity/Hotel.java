package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @OneToMany(mappedBy = "hotel")
    private List<BookHotel> bookHotels;


    @OneToMany(mappedBy = "hotel",fetch = FetchType.EAGER)
    private List<Rate> rates;

}
