package minkavlerne.cafekeabackend.cafe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minkavlerne.cafekeabackend.cafe.entity.Coffee;

@Getter
@Setter
@NoArgsConstructor

public class CoffeeDto {
    private int id;
    private String name;
    private int price;


    public CoffeeDto(Coffee coffee){
        this.id = coffee.getId();
        this.name = coffee.getName();
        this.price = coffee.getPrice();

    }
}
