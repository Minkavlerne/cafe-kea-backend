package minkavlerne.cafekeabackend.cafe.services;

import minkavlerne.cafekeabackend.cafe.dto.CoffeeDto;
import minkavlerne.cafekeabackend.cafe.entity.Coffee;
import minkavlerne.cafekeabackend.cafe.repository.CoffeeRepository;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;
import minkavlerne.cafekeabackend.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    private UserWithRolesRepository userWithRolesRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<CoffeeDto> getAllCoffees() {
        List<Coffee> coffees = coffeeRepository.findAll();
        return coffees.stream().map(CoffeeDto::new).toList();
    }

    public CoffeeDto getCoffeeById(int id) {
        Coffee coffee = coffeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coffee not found"));
        return new CoffeeDto(coffee);
    }

    public CoffeeDto addCoffee(CoffeeDto coffeeDto) {
        Coffee coffee = new Coffee(coffeeDto.getName(), coffeeDto.getPrice());
        coffeeRepository.save(coffee);
        return new CoffeeDto(coffee);
    }
    public UserWithRoles addCoffeeToUser(int userId, int coffeeId) {
        UserWithRoles user = userWithRolesRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow(() -> new RuntimeException("Coffee not found"));
        user.getCoffees().add(coffee);
        return userWithRolesRepository.save(user);
    }
}
