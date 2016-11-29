package eu.pawelniewiadomski.java.spring.micropassmanager.services;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.PasswordData;

public interface PassManagerService {  
  void addPassword(PasswordData passwordData); 
  PasswordData findPassword(PasswordData passwordData);
  void updatePassword(PasswordData passwordData);
  void deletePassword(PasswordData passwordData);
}
