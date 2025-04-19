package Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
