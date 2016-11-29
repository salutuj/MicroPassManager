package eu.pawelniewiadomski.java.spring.micropassmanager.session;

public class User {
  private String id;
  private String masterPassword;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMasterPassword() {
    return masterPassword;
  }

  public void setMasterPassword(String masterPassword) {
    this.masterPassword = masterPassword;
  }
}
