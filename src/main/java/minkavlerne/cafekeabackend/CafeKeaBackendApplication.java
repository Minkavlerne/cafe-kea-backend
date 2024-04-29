package minkavlerne.cafekeabackend;

import minkavlerne.cafekeabackend.cafe.entity.Coffee;
import minkavlerne.cafekeabackend.cafe.repository.CoffeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CafeKeaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeKeaBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner importData(CoffeeRepository coffeeRepository) {
        return args -> {
            coffeeRepository.saveAll(List.of(
                    new Coffee("Latte", 30),
                    new Coffee("Cappuccino", 35),
                    new Coffee("Espresso", 25),
                    new Coffee("Americano", 20)
            ));
        };
    }
}
