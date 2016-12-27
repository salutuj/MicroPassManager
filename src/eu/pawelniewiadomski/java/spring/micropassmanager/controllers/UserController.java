package eu.pawelniewiadomski.java.spring.micropassmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.UserData;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.UserService;

@RestController("userController")
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
  
  @Autowired
  UserService userService;

  @PostMapping(value = { "/" }, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserData> storePassword(@RequestBody UserData userData) {
    userService.storeUser(userData);
    return new ResponseEntity<UserData>(userData, HttpStatus.OK);
  }

  @GetMapping(value = { "/user/{username}" })
  public @ResponseBody ResponseEntity<UserData> getPasswordForUser(@RequestBody UserData userData) {

    UserData userData2 = userService.findPasswordForUser(userData);
    return new ResponseEntity<UserData>(userData2, HttpStatus.OK);
  }

  @PutMapping(value = { "/{username}" })
  @ResponseStatus(HttpStatus.OK)
  public void updatePasswordByKey(@RequestBody UserData userData) {    
    userService.updateUser(userData);
  }

  @DeleteMapping(value = { "/{username}" })
  @ResponseStatus(HttpStatus.OK)
  public void deletePasswordByKey(@RequestBody UserData userData) {    
    userService.deleteUser(userData);
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}
