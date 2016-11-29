package eu.pawelniewiadomski.java.spring.micropassmanager.data;

import java.util.HashMap;

public class PasswordData extends HashMap<String, String>{
  /**
   * 
   */
  private static final long serialVersionUID = -4671030661426781913L;
  
  
  String user;
  String key;
  String login;
  String password;
  
  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
  
  public String getLogin() {
    return login;
  }
  
  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
