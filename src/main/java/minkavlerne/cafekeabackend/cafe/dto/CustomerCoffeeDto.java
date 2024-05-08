package minkavlerne.cafekeabackend.cafe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minkavlerne.cafekeabackend.cafe.entity.CustomerCoffee;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter

public class CustomerCoffeeDto {
    private int id;
    private CoffeeDto coffeeDto;
    private int customerId;
    private LocalDateTime createdAt;

    public CustomerCoffeeDto(CustomerCoffee customerCoffee) {
        this.id = customerCoffee.getId();
        this.coffeeDto = new CoffeeDto(customerCoffee.getCoffee());
        this.customerId = customerCoffee.getCustomer().getId();
        this.createdAt = customerCoffee.getCreatedAt();
    }
}
