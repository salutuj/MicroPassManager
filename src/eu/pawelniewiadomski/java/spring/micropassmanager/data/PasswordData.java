package eu.pawelniewiadomski.java.spring.micropassmanager.data;

public class PasswordData {
  private String key;
  private String login;
  private String password;
  private UserData userData;
  private Object extra;
  
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

  /**
   * @return the extra
   */
  public Object getExtra() {
    return extra;
  }

  /**
   * @param extra the extra to set
   */
  public void setExtra(Object extra) {
    this.extra = extra;
  }

  /**
   * @return the userData
   */
  public UserData getUserData() {
    return userData;
  }

  /**
   * @param userData the userData to set
   */
  public void setUserData(UserData userData) {
    this.userData = userData;
  }

  
}
