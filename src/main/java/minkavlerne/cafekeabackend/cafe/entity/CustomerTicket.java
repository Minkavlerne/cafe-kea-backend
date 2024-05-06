package minkavlerne.cafekeabackend.cafe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class CustomerTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int quantity = 10;

    @ManyToOne
    @JsonBackReference
    private UserWithRoles customer;

    @ManyToOne
    @JsonBackReference
    private Ticket ticket;

}
