package eu.pawelniewiadomski.java.spring.micropassmanager.session;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.UserData;

public class Session {

  private UserData sessionUser;

  public UserData getSessionUser() {
    return sessionUser;
  }

  public void setSessionUser(UserData sessionUser) {
    this.sessionUser = sessionUser;
  }
}
