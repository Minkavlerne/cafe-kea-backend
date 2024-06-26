package minkavlerne.cafekeabackend.security.api;

import minkavlerne.cafekeabackend.security.dto.UserWithRolesPasswordRequest;
import minkavlerne.cafekeabackend.security.dto.UserWithRolesRequest;
import minkavlerne.cafekeabackend.security.dto.UserWithRolesResponse;
import minkavlerne.cafekeabackend.security.entity.Role;
import minkavlerne.cafekeabackend.security.service.UserWithRolesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-with-role")
public class UserWithRoleController {

  //Take care with this. If no role is required for new users, add null as the value below
  static Role DEFAULT_ROLE_TO_ASSIGN = Role.USER;

  UserWithRolesService userWithRolesService;

  public UserWithRoleController(UserWithRolesService userWithRolesService) {
    this.userWithRolesService = userWithRolesService;
  }

  //Anonymous users can call this. Set DEFAULT_ROLE_TO_ASSIGN to null if no role should be added
  @PostMapping
  public UserWithRolesResponse addUserWithRoles(@RequestBody UserWithRolesRequest request) {
    return userWithRolesService.addUserWithRoles (request, DEFAULT_ROLE_TO_ASSIGN);
  }

  //Take care with this. This should NOT be something everyone can call
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/add-role/{username}/{role}")
  public UserWithRolesResponse addRole(@PathVariable int id, @PathVariable String role) {
    return userWithRolesService.addRole(id, Role.fromString(role));
  }
  //Take care with this. This should NOT be something everyone can call
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/remove-role/{username}/{role}")
  public UserWithRolesResponse removeRole(@PathVariable int id, @PathVariable String role) {
    return userWithRolesService.removeRole(id, Role.fromString(role));
  }
  
  @GetMapping("/{email}")
  public UserWithRolesResponse getUserWithRolesByEmail(@PathVariable String email){
    return userWithRolesService.getUserWithRolesByEmail(email);
  }

  // Delete user with roles by email
  //@PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{email}")
  public UserWithRolesResponse deleteUserWithRolesByEmail(@PathVariable String email){
      return userWithRolesService.deleteUserWithRolesByEmail(email);
  }

  // Change password for user
  @PutMapping("/{email}")
  public UserWithRolesResponse editUserWithRolesByEmail(@PathVariable String email, @RequestBody UserWithRolesPasswordRequest request){
    return userWithRolesService.editUserWithRolesByEmail(email, request);
  }

  // Add ticket to user
  @PutMapping("/{email}/ticket")
  public UserWithRolesResponse addTicketToUser(@PathVariable String email, @RequestBody String ticketId){
    return userWithRolesService.addTicketToUser(email, ticketId);
  }

  // Add coffee to user
  @PutMapping("/{email}/coffee")
  public UserWithRolesResponse addCoffeeToUser(@PathVariable String email, @RequestBody String coffeeId){
      return userWithRolesService.addCoffeeToUser(email, coffeeId);
  }

  // subtract quantity of ticket from user
  @PutMapping("/{email}/ticket/{customerTicketId}")
  public UserWithRolesResponse subtractTicketFromUser(@PathVariable String email, @PathVariable String customerTicketId){
    return userWithRolesService.subtractTicketFromUser(email, customerTicketId);
  }

  // Update current CustomerCoffee to be used
  @PutMapping("/{email}/coffee/{customerCoffeeId}")
  public UserWithRolesResponse updateCustomerCoffeeToUsed(@PathVariable String email, @PathVariable String customerCoffeeId){
    return userWithRolesService.updateCustomerCoffeeToUsed(email, customerCoffeeId);
  }
}
