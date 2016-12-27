package eu.pawelniewiadomski.java.spring.micropassmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.UserData;

public class UserDao extends NamedParameterJdbcDaoSupport  {

  
  private static final String USER_QUERYPASSWORD = "" +
      "SELECT password " +
      "FROM users " +
      "WHERE username =?";

  private static final String USER_INSERT = "" +
      "INSERT INTO users (username, password) " +
      "VALUES (?, ?)";
  
  private static final String USER_UPDATE = "" +
      "UPDATE users SET (password=?) " +
      "WHERE username=?";
  
  private static final String USER_DELETE = "" +
      "DELETE FROM users WHERE (username =?)";

  
  public UserData findPasswordForUser(final String username) {
     List<?> users = getJdbcTemplate().query(USER_QUERYPASSWORD, new Object[] {
        username },
        new RowMapper<UserData>() {
          @Override
          public UserData mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
            UserData user = new UserData();
            user.setUsername(username);
            user.setPassword((rs.getString(1)));            
            return user;
          }
        });
    return (UserData) (users.size() > 0 ? users.get(0) : null); 
  }
  
  public boolean addUser(final String username, final String password) {
    int rowsAffected = getJdbcTemplate().update(USER_INSERT, new Object[] {
       username, password });
    return rowsAffected == 1;
  }
  
  public boolean updateUser(final String username, final String password) {
    int rowsAffected = getJdbcTemplate().update(USER_UPDATE, new Object[] {
       password, username });
    return rowsAffected == 1;
  }
  
  public boolean deleteUser(final String username, final String password) {
    int rowsAffected = getJdbcTemplate().update(USER_DELETE, new Object[] {
       username });
    return rowsAffected == 1;
  }
  
  
}
