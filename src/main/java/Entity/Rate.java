package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rate")
public class Rate extends AbstractEntity<Long> {
    private String comment;
    private float rateStar;

    @ManyToOne
    private Customer customer;


    @ManyToOne
    private Hotel hotel;
}
