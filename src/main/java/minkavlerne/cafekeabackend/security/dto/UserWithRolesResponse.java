package minkavlerne.cafekeabackend.security.dto;


import lombok.Getter;
import lombok.Setter;
import minkavlerne.cafekeabackend.cafe.dto.CoffeeDto;
import minkavlerne.cafekeabackend.cafe.dto.CustomerCoffeDto;
import minkavlerne.cafekeabackend.cafe.dto.CustomerTicketDto;
import minkavlerne.cafekeabackend.cafe.dto.TicketDto;
import minkavlerne.cafekeabackend.cafe.entity.CustomerCoffee;
import minkavlerne.cafekeabackend.cafe.entity.CustomerTicket;
import minkavlerne.cafekeabackend.cafe.entity.Ticket;
import minkavlerne.cafekeabackend.cafe.entity.Coffee;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserWithRolesResponse {
    List<String> roleNames;
    String email;
    List<CustomerTicketDto> tickets;
    List<CustomerCoffeDto> coffees;


    public UserWithRolesResponse(UserWithRoles userWithRoles){
        this.roleNames = userWithRoles.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        this.email = userWithRoles.getEmail();
        List<CustomerTicket> oldCustomerTickets = userWithRoles.getCustomerTickets();
        this.tickets = oldCustomerTickets.stream().map(CustomerTicketDto::new).collect(Collectors.toList());
        List<CustomerCoffee> oldCoffees = userWithRoles.getCustomerCoffees();
        this.coffees = oldCoffees.stream().map(CustomerCoffeDto::new).collect(Collectors.toList());
    }

}
