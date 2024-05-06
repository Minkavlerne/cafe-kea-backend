package minkavlerne.cafekeabackend.cafe.api;

import minkavlerne.cafekeabackend.cafe.dto.CoffeeDto;
import minkavlerne.cafekeabackend.cafe.services.CoffeeService;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee")

public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }
   @GetMapping
   public ResponseEntity<List<CoffeeDto>> getAllCoffees() {
       return ResponseEntity.ok(coffeeService.getAllCoffees());
   }
   @GetMapping("/{id}")
    public ResponseEntity<CoffeeDto> getCoffeeById(@PathVariable int id) {
         return ResponseEntity.ok(coffeeService.getCoffeeById(id));
    }




}
