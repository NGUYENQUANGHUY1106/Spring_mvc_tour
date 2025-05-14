package Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "favoritehotel")
public class FavoriteHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long hotelId;
    private String hotelName;
    private String hotelImage;
    private Double hotelRating;
    private Double hotelPrice;
    private String hotelAddress;
    private Integer availableRooms;

    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getHotelId() { return hotelId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public String getHotelImage() { return hotelImage; }
    public void setHotelImage(String hotelImage) { this.hotelImage = hotelImage; }

    public Double getHotelRating() { return hotelRating; }
    public void setHotelRating(Double hotelRating) { this.hotelRating = hotelRating; }

    public Double getHotelPrice() { return hotelPrice; }
    public void setHotelPrice(Double hotelPrice) { this.hotelPrice = hotelPrice; }

    public String getHotelAddress() { return hotelAddress; }
    public void setHotelAddress(String hotelAddress) { this.hotelAddress = hotelAddress; }

    public Integer getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(Integer availableRooms) { this.availableRooms = availableRooms; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
