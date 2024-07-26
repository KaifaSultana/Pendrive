import java.util.ArrayList;
import java.util.Scanner;

class User {
    String name;
    String userName;
    String password;
    String role;

    User(String name, String userName, String password, String role) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getRole() 
    {
        return role;
    }

    public String getName() 
    {
        return name;
    }

    public String getUserName() 
    {
        return userName;
    }

    public String getPassword() 
    {
        return password;
    }

    @Override
    public String toString() {
        return "Name: " + name +", "+ " Username: " + userName +", "+ " Password: " + password +", "+ " Role: " + role;
    }
}