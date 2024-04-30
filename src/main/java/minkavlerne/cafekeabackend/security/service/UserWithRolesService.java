package minkavlerne.cafekeabackend.security.service;

import minkavlerne.cafekeabackend.security.dto.UserWithRolesRequest;
import minkavlerne.cafekeabackend.security.dto.UserWithRolesResponse;
import minkavlerne.cafekeabackend.security.entity.Role;
import minkavlerne.cafekeabackend.security.entity.UserWithRoles;
import minkavlerne.cafekeabackend.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserWithRolesService {

  private  final UserWithRolesRepository userWithRolesRepository;

  public UserWithRolesService(UserWithRolesRepository userWithRolesRepository) {
    this.userWithRolesRepository = userWithRolesRepository;
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

}
