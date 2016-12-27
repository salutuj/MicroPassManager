package eu.pawelniewiadomski.java.spring.micropassmanager.services.impl;

import eu.pawelniewiadomski.java.spring.micropassmanager.dao.UserDao;
import eu.pawelniewiadomski.java.spring.micropassmanager.data.UserData;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.UserService;

public class DefaultUserService implements UserService{

  UserDao userDao;
  
  @Override
  public void storeUser(UserData userData) {
    userDao.addUser(userData.getUsername(), userData.getPassword());    
  }

  @Override
  public UserData findPasswordForUser(UserData userData) {
    UserData password = userDao.findPasswordForUser(userData.getUsername());    
    return password;
  }

  @Override
  public void updateUser(UserData userData) {
    userDao.updateUser(userData.getUsername(), userData.getPassword());    
  }

  @Override
  public void deleteUser(UserData userData) {
    // TODO Auto-generated method stub
    
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserData findUser(UserData userData) {
    // TODO Auto-generated method stub
    return null;
  }
}
