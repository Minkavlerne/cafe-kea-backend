package minkavlerne.cafekeabackend.cafe.services;

import minkavlerne.cafekeabackend.cafe.dto.CoffeeDto;
import minkavlerne.cafekeabackend.cafe.entity.Coffee;
import minkavlerne.cafekeabackend.cafe.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<CoffeeDto> getAllCoffees() {
        List<Coffee> coffees = coffeeRepository.findAll();
        return coffees.stream().map(CoffeeDto::new).toList();
    }
}
