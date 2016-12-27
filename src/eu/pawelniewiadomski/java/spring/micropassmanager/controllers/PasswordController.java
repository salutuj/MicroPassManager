package eu.pawelniewiadomski.java.spring.micropassmanager.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import eu.pawelniewiadomski.java.spring.micropassmanager.data.PasswordData;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.PassManagerService;

/**
 * 
 * @author pawel.niewiadomski
 */

@RestController("passwordController")
@RequestMapping(value = "/password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PasswordController {

  protected static final Log LOG = LogFactory.getLog(PasswordController.class);

  @Autowired
  PassManagerService passManagerService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PasswordData> findAllPasswords(@RequestBody PasswordData passwordData) {
    return passManagerService.findAllPasswords(passwordData);
  }
  
  @PostMapping(value = { "/" }, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<PasswordData> storePassword(@RequestBody PasswordData passwordData) {
    passManagerService.addPassword(passwordData);
    return new ResponseEntity<PasswordData>(passwordData, HttpStatus.OK);
  }

  @GetMapping(value = { "/user/{user}/key/{key}" })
  public @ResponseBody String getPasswordByKey(@PathVariable PasswordData passwordData) {

    PasswordData password = passManagerService.findPassword(passwordData);
    return "{'result' : 'ok', 'data' : { 'password' : '" + password + "'}";
  }

  @PutMapping(value = { "/{key}" })
  @ResponseStatus(HttpStatus.OK)
  public void updatePasswordByKey(@RequestBody PasswordData passwordData) {    
    passManagerService.updatePassword(passwordData);
  }

  @DeleteMapping(value = { "/{key}" })
  @ResponseStatus(HttpStatus.OK)
  public void deletePasswordByKey(@RequestBody PasswordData passwordData) {    
    passManagerService.deletePassword(passwordData);
  }

  public void setPassManagerService(PassManagerService passManagerService) {
    this.passManagerService = passManagerService;
  }

}
