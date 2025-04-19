package Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class RoleEntity  extends  AbstractEntity<Integer>   {

    private String name;
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<UserHasRole> hasRoleSet;


}
