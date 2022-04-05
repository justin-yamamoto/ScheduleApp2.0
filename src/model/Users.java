package model;
/**Sets parameters for Users. */
public class Users {

    int userId;
    String userName;
    String password;

    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    /**Get user ID.
     * @return user ID.*/
    public int getUserId() {
        return userId;
    }
    /**Set user ID. */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**Get username.
     * @return username. */
    public String getUserName() {
        return userName;
    }
    /**Set username. */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**Gets password.
     * @return password. */
    public String getPassword() {
        return password;
    }
    /**Set password. */
    public void setPassword(String password) {
        this.password = password;
    }

    /**Set Combo Box to  readable Users. */
    public String toString(){
        return("ID: " + userId + " " + "User: " + userName);
    }
}
