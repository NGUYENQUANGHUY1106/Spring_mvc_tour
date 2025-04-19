package Service;

import Entity.RoleEntity;
import Entity.UserEntity;
import Entity.UserHasRole;
import Enum1.EnumRole;
import Repository.RoleRepository;
import Repository.UserHasRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class UserHasRoleService {
    @Autowired
    private UserHasRoleRepository userHasRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserHasRole userHasRoleHotel(UserEntity user) {
        RoleEntity role = roleRepository.findByName(EnumRole.HOTEL.name());
        UserHasRole userHasRole = new UserHasRole();
        userHasRole.setUser(user);
        userHasRole.setRole(role);
        return userHasRoleRepository.save(userHasRole);
    }

    public UserHasRole userHasRoleCustomer(UserEntity user) {
        RoleEntity role = roleRepository.findByName(EnumRole.CUSTOMER.name());
        UserHasRole userHasRole = new UserHasRole();
        userHasRole.setUser(user);
        userHasRole.setRole(role);
        return userHasRoleRepository.save(userHasRole);
    }
}
