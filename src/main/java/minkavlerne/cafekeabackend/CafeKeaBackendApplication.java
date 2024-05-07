package minkavlerne.cafekeabackend;

import minkavlerne.cafekeabackend.cafe.entity.Coffee;
import minkavlerne.cafekeabackend.cafe.entity.CustomerTicket;
import minkavlerne.cafekeabackend.cafe.entity.Ticket;
import minkavlerne.cafekeabackend.cafe.repository.CoffeeRepository;
import minkavlerne.cafekeabackend.cafe.repository.CustomerTicketRepository;
import minkavlerne.cafekeabackend.cafe.repository.TicketRepository;
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
    public CommandLineRunner importData(CoffeeRepository coffeeRepository, UserWithRolesRepository userWithRolesRepository, PasswordEncoder pwEncoder, TicketRepository ticketRepository) {

        return args -> {
            /*coffeeRepository.saveAll(List.of(
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

            Ticket ticket1 = new Ticket(100, "10 small basics");
            Ticket ticket2 = new Ticket(150, "10 large basics");
            Ticket ticket3 = new Ticket(200, "10 small specials");
            Ticket ticket4 = new Ticket(250, "10 large specials");
            ticketRepository.saveAll(List.of(ticket1, ticket2, ticket3, ticket4));*/

            /*CustomerTicket customerTicket1 = new CustomerTicket();
            customerTicket1.setTicket(ticketRepository.findById(1).get());
            customerTicket1.setCustomer(userWithRolesRepository.findById(1).get());
            customerTicketRepository.save(customerTicket1);*/

        };
    }
}
