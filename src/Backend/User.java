package Backend;

import java.util.ArrayList;

public class User {
    private int ID;
    private String name;
    private String email;
    private String password;
    private ArrayList<Notification> notifications;
    private UserVoucherMap voucherMap;
    private UserType userType;
    public enum UserType {
        ADMIN,
        GUEST
    }

    public User(int ID, String name, String email, String password, UserType userType) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        voucherMap = new UserVoucherMap();
        notifications = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public UserVoucherMap getVoucherMap() {
        return voucherMap;
    }

    public void setVoucherMap(UserVoucherMap voucherMap) {
        this.voucherMap = voucherMap;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                '}';
    }
}
