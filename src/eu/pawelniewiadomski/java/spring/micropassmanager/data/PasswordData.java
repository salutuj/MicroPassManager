package eu.pawelniewiadomski.java.spring.micropassmanager.data;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PasswordData implements Map<String, String>{
  String user;
  String serviceId;
  String password;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean containsKey(Object key) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean containsValue(Object value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String get(Object key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String put(String key, String value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String remove(Object key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void putAll(Map<? extends String, ? extends String> m) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Set<String> keySet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Collection<String> values() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<java.util.Map.Entry<String, String>> entrySet() {
    // TODO Auto-generated method stub
    return null;
  }
}
