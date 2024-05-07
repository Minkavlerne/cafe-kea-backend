package minkavlerne.cafekeabackend.cafe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class CustomerCoffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonBackReference
    private UserWithRoles customer;

    @ManyToOne
    @JsonBackReference
    private Coffee coffee;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public CustomerCoffee(UserWithRoles customer, Coffee coffee) {
        this.customer = customer;
        this.coffee = coffee;
    }
}
