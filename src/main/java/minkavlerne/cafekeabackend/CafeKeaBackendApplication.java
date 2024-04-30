package minkavlerne.cafekeabackend;

import minkavlerne.cafekeabackend.cafe.entity.Coffee;
import minkavlerne.cafekeabackend.cafe.repository.CoffeeRepository;
import minkavlerne.cafekeabackend.security.entity.Role;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;
import minkavlerne.cafekeabackend.security.repository.UserWithRolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CafeKeaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeKeaBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner importData(CoffeeRepository coffeeRepository, UserWithRolesRepository userWithRolesRepository, PasswordEncoder pwEncoder) {
        return args -> {
            coffeeRepository.saveAll(List.of(
                    new Coffee("Small Filter", 15),
                    new Coffee("Large Filter", 20),
                    new Coffee("Small Latte", 25),
                    new Coffee("Large Latte", 30),
                    new Coffee("Small Cappuccino", 25),
                    new Coffee("Large Cappuccino", 30),
                    new Coffee("Espresso", 20),
                    new Coffee("Americano", 20),
                    new Coffee("Small Tea", 10),
                    new Coffee("Large Tea", 15)
            ));

            String passwordByAll = "test123";
            UserWithRoles user1 = new UserWithRoles();
            user1.setPassword(pwEncoder.encode(passwordByAll));
            user1.setEmail("test@test.dk");
            user1.addRole(Role.USER);
            userWithRolesRepository.save(user1);

        };
    }
}
