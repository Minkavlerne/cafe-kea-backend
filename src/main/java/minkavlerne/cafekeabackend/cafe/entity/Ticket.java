package minkavlerne.cafekeabackend.cafe.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private int price;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "ticket")
    @JsonManagedReference
    private List<CustomerTicket> customerTickets = new ArrayList<>();



}
