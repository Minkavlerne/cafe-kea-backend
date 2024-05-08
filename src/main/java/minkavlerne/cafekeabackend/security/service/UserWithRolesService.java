package minkavlerne.cafekeabackend.security.service;

import minkavlerne.cafekeabackend.cafe.entity.CustomerCoffee;
import minkavlerne.cafekeabackend.cafe.entity.CustomerTicket;
import minkavlerne.cafekeabackend.cafe.entity.Ticket;
import minkavlerne.cafekeabackend.cafe.repository.CustomerCoffeeRepository;
import minkavlerne.cafekeabackend.cafe.repository.CustomerTicketRepository;
import minkavlerne.cafekeabackend.cafe.repository.TicketRepository;
import minkavlerne.cafekeabackend.cafe.entity.Coffee;
import minkavlerne.cafekeabackend.cafe.repository.CoffeeRepository;
import minkavlerne.cafekeabackend.security.dto.UserWithRolesPasswordRequest;
import minkavlerne.cafekeabackend.security.dto.UserWithRolesRequest;
import minkavlerne.cafekeabackend.security.dto.UserWithRolesResponse;
import minkavlerne.cafekeabackend.security.entity.Role;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;
import minkavlerne.cafekeabackend.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserWithRolesService {

  private  final UserWithRolesRepository userWithRolesRepository;
  private final TicketRepository ticketRepository;
  private final CustomerTicketRepository customerTicketRepository;
  private final CustomerCoffeeRepository customerCoffeReporsitory;
  private  final CoffeeRepository coffeeRepository;

  public UserWithRolesService(UserWithRolesRepository userWithRolesRepository, TicketRepository ticketRepository, CustomerTicketRepository customerTicketRepository, CoffeeRepository coffeeRepository, CustomerCoffeeRepository customerCoffeReporsitory) {
    this.userWithRolesRepository = userWithRolesRepository;
    this.ticketRepository = ticketRepository;
    this.customerTicketRepository = customerTicketRepository;
    this.coffeeRepository = coffeeRepository;
    this.customerCoffeReporsitory = customerCoffeReporsitory;
  }

  public UserWithRolesResponse getUserWithRoles(int id){
    UserWithRoles user = userWithRolesRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    return new UserWithRolesResponse(user);
  }

  public UserWithRolesResponse getUserWithRolesByEmail(String email){
    UserWithRoles user = userWithRolesRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    return new UserWithRolesResponse(user);
  }

  //Make sure that this can ONLY be called by an admin
  public UserWithRolesResponse addRole(int id , Role role){
    UserWithRoles user = userWithRolesRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    user.addRole(role);
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  //Make sure that this can ONLY be called by an admin
  public UserWithRolesResponse removeRole(int id , Role role){

    UserWithRoles user = userWithRolesRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    user.removeRole(role);
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  //Only way to change roles is via the addRole method
  public UserWithRolesResponse editUserWithRoles(int id , UserWithRolesRequest body){
    UserWithRoles user = userWithRolesRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    user.setEmail(body.getEmail());
    user.setPassword(body.getPassword());
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  /**
   *
   * @param body - the user to be added
   * @param role  - the role to be added to the user - pass null if no role should be added
   * @return the user added
   */
  public UserWithRolesResponse addUserWithRoles(UserWithRolesRequest body, Role role){
    if(userWithRolesRepository.existsById(body.getId())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This email is used by another user");
    }
    if(userWithRolesRepository.existsByEmail(body.getEmail())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This email is used by another user");
    }
    String pw = body.getPassword();
    UserWithRoles userWithRoles = new UserWithRoles(body.getId(), pw, body.getEmail());
    if(role !=null  ) {
      userWithRoles.addRole(role);
    }
    return new UserWithRolesResponse(userWithRolesRepository.save(userWithRoles));
  }
  
  public UserWithRolesResponse deleteUserWithRolesByEmail(String email){
      UserWithRoles user = userWithRolesRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
      userWithRolesRepository.delete(user);
      return new UserWithRolesResponse(user);
  }
  
  public UserWithRolesResponse editUserWithRolesByEmail(String email, UserWithRolesPasswordRequest request){
      UserWithRoles user = userWithRolesRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
      user.setPassword(request.getPassword());
      return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  public UserWithRolesResponse addTicketToUser(String email, String ticketId) {
      int id = Integer.parseInt(ticketId);
      UserWithRoles user = userWithRolesRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
      Ticket newTicket = ticketRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket not found"));
      CustomerTicket newCustomerTicket = new CustomerTicket(user, newTicket);
      customerTicketRepository.save(newCustomerTicket);
      List<CustomerTicket> oldCustomerTickets = user.getCustomerTickets();
      oldCustomerTickets.add(newCustomerTicket);
      user.setCustomerTickets(oldCustomerTickets);
      return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  public UserWithRolesResponse addCoffeeToUser(String email, String coffeeId){
      int id = Integer.parseInt(coffeeId);
      UserWithRoles user = userWithRolesRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
      Coffee coffee = coffeeRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Coffee not found"));
      CustomerCoffee newCustomerCoffee = new CustomerCoffee(user, coffee);
      customerCoffeReporsitory.save(newCustomerCoffee);
      List<CustomerCoffee> oldCustomerCoffees = user.getCustomerCoffees();
      oldCustomerCoffees.add(newCustomerCoffee);
      user.setCustomerCoffees(oldCustomerCoffees);
      return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

    public UserWithRolesResponse subtractTicketFromUser(String email, String ticketId) {
        int id = Integer.parseInt(ticketId);
        UserWithRoles user = userWithRolesRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        CustomerTicket customerTicket = customerTicketRepository.findByCustomerIdAndTicketId(user.getId(), id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CustomerTicket not found"));
        int currentQuantity = customerTicket.getQuantity();
        customerTicket.setUsed(currentQuantity == 1);
        customerTicket.setQuantity(currentQuantity - 1);
        customerTicketRepository.save(customerTicket);
        return new UserWithRolesResponse(user);
    }

    public UserWithRolesResponse updateCustomerCoffeeToUsed(String email, String coffeeId) {
        int id = Integer.parseInt(coffeeId);
        UserWithRoles user = userWithRolesRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        CustomerCoffee customerCoffee = customerCoffeReporsitory.findByCustomerIdAndCoffeeId(user.getId(), id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CustomerCoffee not found"));
        customerCoffee.setUsed(true);
        customerCoffeReporsitory.save(customerCoffee);
        return new UserWithRolesResponse(user);
    }
}
