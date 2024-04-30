package minkavlerne.cafekeabackend.security.dto;



import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRolesRequest {
    @NonNull
    int id;
    @NonNull
    String password;
    @NonNull
    String email;
}