package minkavlerne.cafekeabackend.cafe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minkavlerne.cafekeabackend.cafe.entity.CustomerCoffee;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter

public class CustomerCoffeDto {
    private int id;
    private int coffeeId;
    private int customerId;
    private LocalDateTime createdAt;

    public CustomerCoffeDto(CustomerCoffee customerCoffee) {
        this.id = customerCoffee.getId();
        this.coffeeId = customerCoffee.getCoffee().getId();
        this.customerId = customerCoffee.getCustomer().getId();
        this.createdAt = customerCoffee.getCreatedAt();
    }
}
