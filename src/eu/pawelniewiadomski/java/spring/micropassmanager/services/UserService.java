package eu.pawelniewiadomski.java.spring.micropassmanager.services;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.UserData;

public interface UserService {  
  void storeUser(UserData userData); 
  UserData findUser(UserData userData);
  void updateUser(UserData userData);
  void deleteUser(UserData userData);
  UserData findPasswordForUser(UserData userData);  
}
