package minkavlerne.cafekeabackend.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithRolesPasswordRequest {
    @NonNull
    String password;
    @NonNull
    String email;

}
