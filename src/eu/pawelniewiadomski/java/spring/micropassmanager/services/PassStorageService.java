package eu.pawelniewiadomski.java.spring.micropassmanager.services;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.PasswordData;

public interface PassStorageService {
  PasswordData readPassword(PasswordData passwordData);
  void storePassword(PasswordData passwordData);
  void updatePassword(PasswordData passwordData);
  void removePassword(PasswordData passwordData);
}
