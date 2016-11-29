package eu.pawelniewiadomski.java.spring.micropassmanager.session;

public class Session {

  private User sessionUser;

  public User getSessionUser() {
    return sessionUser;
  }

  public void setSessionUser(User sessionUser) {
    this.sessionUser = sessionUser;
  }
}
