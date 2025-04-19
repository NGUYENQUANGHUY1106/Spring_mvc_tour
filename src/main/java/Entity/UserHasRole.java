package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_has_role")
public class UserHasRole extends AbstractEntity<Integer> {
    @ManyToOne
    private RoleEntity role;

    @ManyToOne
    private UserEntity user;
}
