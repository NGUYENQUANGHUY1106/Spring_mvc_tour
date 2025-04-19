package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
@Table(name = "address_hotel")
public class AddressHotel extends AbstractEntity<Long> {
    private String province;
    private String district;
    private String town;
    private String detail;

    @OneToOne(mappedBy = "addressHotel")
    private Hotel hotel;
}
