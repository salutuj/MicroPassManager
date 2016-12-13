package eu.pawelniewiadomski.java.spring.micropassmanager.services;

import java.util.List;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.PasswordData;

public interface PassManagerService {  
  void addPassword(PasswordData passwordData); 
  PasswordData findPassword(PasswordData passwordData);
  void updatePassword(PasswordData passwordData);
  void deletePassword(PasswordData passwordData);
  List<PasswordData> findAllPasswords(PasswordData passwordData);
}
