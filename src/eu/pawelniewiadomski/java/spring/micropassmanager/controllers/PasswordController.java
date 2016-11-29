package eu.pawelniewiadomski.java.spring.micropassmanager.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.PasswordData;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.PassManagerService;

/**
 * 
 * @author pawel.niewiadomski
 */

@Controller("passwordController")
@RequestMapping( value="/password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PasswordController {

  protected static final Log LOG = LogFactory.getLog(PasswordController.class);
  
  @Autowired
  PassManagerService passManagerService;
  
  
  @PostMapping(value = { "/" }, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody String addPassword(@RequestBody PasswordData password) {    
    passManagerService.addPasswordForKey(passwordData);
    return "{'result' : 'ok'}";
  }
  
  @GetMapping(value = { "/user/{user}/key/{key}" })
  public @ResponseBody String getPasswordByKey(@PathVariable PasswordData password) {    
    
    PassManagerService defaultFamily = passManagerService.findPasswordByKey(null);
    return "{ok}";
  }
  
  @PutMapping(value = { "/{key}" })
  public @ResponseBody String updatePasswordByKey(@PathVariable String key, String password) {    
    
    PassManagerService defaultFamily = passManagerService.updatePasswordForKey(key, password);
    return "{ok}";
  }
  
  @DeleteMapping(value = { "/{key}" })
  public @ResponseBody String deletePasswordByKey(@PathVariable String key) {
    boolean deleted = false; 
    try {
     passManagerService.deletePasswordForKey(key);
    } catch (NullPointerException ex){
      return "{'result' : null, 'exception': 'true', 'data' : '" +ex.getMessage();
    }
    return "{ result: }";
  }
  
  
  

}
