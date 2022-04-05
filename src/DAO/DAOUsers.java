package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This gets user data. */
public abstract class DAOUsers {

     /**Gets all user data.
      * @return  True if input matches
      * @return  False if input does not match. */
     public static boolean select(String userName, String password) throws SQLException {
          String sql = "SELECT * FROM USERS WHERE User_Name =? AND Password=?";
          PreparedStatement ps = JDBC.connection.prepareStatement(sql);
          ps.setString(1, userName);
          ps.setString(2, password);
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {

               return true;

          }

          return false;
     }
     /**Get list of all users. */
     public static ObservableList<Users> getAllUsers()

     {
          ObservableList<Users> usersList = FXCollections.observableArrayList();

          try {
               String sql = "SELECT * FROM USERS ";

               PreparedStatement ps = JDBC.connection.prepareStatement(sql);

               ResultSet rs = ps.executeQuery();
               while (rs.next()) {
                    int userId = rs.getInt("User_ID");
                    String userName = rs.getString("User_Name");
                    String password = rs.getString("Password");

                    Users newUser = new Users(userId, userName, password);
                    usersList.add(newUser);

               }
          } catch (SQLException e) {
               e.printStackTrace();
          }

          return usersList;
     }


}
