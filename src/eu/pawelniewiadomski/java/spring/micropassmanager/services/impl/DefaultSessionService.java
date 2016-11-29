package eu.pawelniewiadomski.java.spring.micropassmanager.services.impl;

import eu.pawelniewiadomski.java.spring.micropassmanager.services.SessionService;
import eu.pawelniewiadomski.java.spring.micropassmanager.session.Session;

public class DefaultSessionService implements SessionService{

  private Session currentSession;
  
  @Override
  public Session getCurrentSession() {
    return currentSession;
  }

}
