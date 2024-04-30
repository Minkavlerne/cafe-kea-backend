package minkavlerne.cafekeabackend.security.dto;


import lombok.Getter;
import lombok.Setter;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserWithRolesResponse {
    List<String> roleNames;
    String email;

    public UserWithRolesResponse(UserWithRoles userWithRoles){
        this.roleNames = userWithRoles.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        this.email = userWithRoles.getEmail();
    }

}
