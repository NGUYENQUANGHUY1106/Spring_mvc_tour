package Repository;

import Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT r.name FROM UserEntity u " +
            "JOIN u.userHasRoles uhr " +
            "JOIN uhr.role r " +
            "WHERE u.email = :email")
    Optional<List<String>> findUserGetPermisstion(@Param("email") String email);
}
