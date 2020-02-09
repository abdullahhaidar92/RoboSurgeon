package models;
import java.sql.Date;

public abstract class  User extends Profile {

    private int userId;
    private String userName;
    private String password;
    private String role;
    
    public User(){};
    
    public User(int profileId, String firstName, String middleName, String lastName) {
        super(profileId, firstName, middleName, lastName);
    }

    public User(int profileId, String firstName, String middleName, String lastName, String email, String phone, Date birthDate, String address, String bloodType) {
        super(profileId, firstName, middleName, lastName, email, phone, birthDate, address, bloodType);
    }
    
 
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
