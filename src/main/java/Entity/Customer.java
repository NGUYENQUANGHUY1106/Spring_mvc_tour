package Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "customer")
public class Customer extends AbstractEntity<Long> {
    private String username;
    private String phone;

    @OneToOne
    private UserEntity user;


    @OneToMany(mappedBy = "customer")
    private List<BookHotel> bookHotels;

    @OneToMany(mappedBy = "customer")
    private List<Rate> rates;

}
