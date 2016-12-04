package eu.pawelniewiadomski.java.spring.micropassmanager.services.impl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.Constants;
import eu.pawelniewiadomski.java.spring.micropassmanager.data.PasswordData;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.CryptoService;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.PassManagerService;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.PassStorageService;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.SessionService;
import eu.pawelniewiadomski.java.spring.micropassmanager.session.User;

public class DefaultPassManagerService implements PassManagerService {
  protected static final Log LOG = LogFactory.getLog(DefaultPassManagerService.class);

  private CryptoService cryptoService;
  private SessionService sessionService;
  private PassStorageService storageService;

  @Override
  public void addPassword(PasswordData passwordData) {
    if (verifyUser(passwordData.get(Constants.USER))) {
      try {

        PasswordData encryptedPassword = new PasswordData();
        encryptedPassword.setUser(passwordData.getUser());
        encryptedPassword.setKey(passwordData.getKey());
        encryptedPassword.setPassword(cryptoService.encryptString(makeCipherKey(passwordData), passwordData.getPassword()));
        storageService.storePassword(encryptedPassword);

      } catch (DataLengthException | IllegalStateException | InvalidCipherTextException | IOException e) {
        LOG.error("Exception occured:", e);
        throw new RuntimeException(e);
      }
    } else throw new RuntimeException("Cannot verify user");
  }

  @Override
  public PasswordData findPassword(PasswordData passwordData) {
    if (verifyUser(passwordData.get(Constants.USER))) {
      try {
        PasswordData readPassword = storageService.readPassword(passwordData);
        if ( readPassword != null)       
        readPassword.setPassword(cryptoService.encryptString(makeCipherKey(passwordData), passwordData.getPassword()));
        return readPassword;

      } catch (DataLengthException | IllegalStateException | InvalidCipherTextException | IOException e) {
        LOG.error("Exception occured:", e);
        throw new RuntimeException(e);
      }
    } else throw new RuntimeException("Cannot verify user");    
  }

  @Override
  public void updatePassword(PasswordData passwordData) {
    if (verifyUser(passwordData.get(Constants.USER))) {
      try {

        PasswordData encryptedPassword = new PasswordData();
        encryptedPassword.setUser(passwordData.getUser());
        encryptedPassword.setKey(passwordData.getKey());
        encryptedPassword.setPassword(cryptoService.encryptString(makeCipherKey(passwordData), passwordData.getPassword()));
        storageService.storePassword(encryptedPassword);

      } catch (DataLengthException | IllegalStateException | InvalidCipherTextException | IOException e) {
        LOG.error("Exception occured:", e);
        throw new RuntimeException(e);
      }
    } else throw new RuntimeException("Cannot verify user");
  }

  @Override
  public void deletePassword(PasswordData passwordData) {
    if (verifyUser(passwordData.get(Constants.USER))) {
      try {

        PasswordData encryptedPassword = new PasswordData();
        encryptedPassword.setUser(passwordData.getUser());
        encryptedPassword.setKey(passwordData.getKey());
        encryptedPassword.setPassword(cryptoService.encryptString(makeCipherKey(passwordData), passwordData.getPassword()));
        storageService.storePassword(encryptedPassword);

      } catch (DataLengthException | IllegalStateException | InvalidCipherTextException | IOException e) {
        LOG.error("Exception occured:", e);
        throw new RuntimeException(e);
      }
    } else throw new RuntimeException("Cannot verify user");
  }

  private String makeCipherKey(PasswordData passwordData) {
    User user = sessionService.getCurrentSession().getSessionUser();
    return user.getId() + user.getMasterPassword() + passwordData.getKey();
  }

  private boolean verifyUser(String userId) {
    User currentUser = sessionService.getCurrentSession().getSessionUser();
    if (currentUser != null)
      return !userId.equals(currentUser.getId());
    else throw new RuntimeException("No user defined for session");

  }

  public CryptoService getCryptoService() {
    return cryptoService;
  }

  public void setCryptoService(CryptoService cryptoService) {
    this.cryptoService = cryptoService;
  }
  
  public SessionService getSessionService() {
    return sessionService;
  }
  
  public void setSessionService(SessionService sessionService) {
    this.sessionService = sessionService;
  }
  
  public PassStorageService getStorageService() {
    return storageService;
  }
  
  public void setStorageService(PassStorageService storageService) {
    this.storageService = storageService;
  }

}
