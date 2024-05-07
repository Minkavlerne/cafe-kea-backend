package minkavlerne.cafekeabackend.security.dto;


import lombok.Getter;
import lombok.Setter;
import minkavlerne.cafekeabackend.cafe.dto.CoffeeDto;
import minkavlerne.cafekeabackend.cafe.dto.TicketDto;
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
    List<TicketDto> tickets;
    List<CoffeeDto> coffees;

    public UserWithRolesResponse(UserWithRoles userWithRoles){
        this.roleNames = userWithRoles.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        this.email = userWithRoles.getEmail();
        List<Ticket> oldTickets = userWithRoles.getCustomerTickets().stream().map(CustomerTicket::getTicket).toList();
        this.tickets = oldTickets.stream().map(TicketDto::new).collect(Collectors.toList());
        List<Coffee> oldCoffees = userWithRoles.getCoffees();
        this.coffees = oldCoffees.stream().map(CoffeeDto::new).collect(Collectors.toList());
    }

}
