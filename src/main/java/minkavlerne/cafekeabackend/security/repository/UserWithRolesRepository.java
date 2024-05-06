package minkavlerne.cafekeabackend.security.repository;

import minkavlerne.cafekeabackend.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface
UserWithRolesRepository extends JpaRepository<UserWithRoles,Integer> {
    Boolean existsByEmail(String email);
    Optional<UserWithRoles> findByEmail(String email);

}
