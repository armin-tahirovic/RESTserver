package CommunicateWithData.User;

import java.sql.*;
import java.util.ArrayList;

public class CallDatabase {
    private ArrayList<User> users = new ArrayList<>();
    private Connection c = null;
    private Statement s = null;

    private CallDatabase() {
    }
    private static CallDatabase callDatabase = null;
    public static CallDatabase CallDB() {
        if (callDatabase == null) {
            callDatabase = new CallDatabase();
        }
        return callDatabase;
    }

    public void makeConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            s = c.createStatement();
        }
        catch (SQLException | ClassNotFoundException e ){
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUser() {
        makeConnection();
        try{
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".customer;");
            while (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(username,password));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public User postUser(User user) {
        System.out.println(user);
        makeConnection();
        try {


            String createUserSQL = "INSERT INTO \"sep3\".customer VALUES ('"+ user.getUsername() +"', '"+ user.getPassword() +"');";
            s.executeQuery(createUserSQL);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(user.getUsername(), user.getPassword());
    }

    public void putPassword(String username, String password) {
        makeConnection();
        try {
            System.out.println(username + password);
            String createUserSQL = "UPDATE \"sep3\".customer set kode = '"+ password +"' where username = '"+ username +"'";
            s.executeQuery(createUserSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getOneUser(String username) {
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".customer WHERE username = '"+ username +"';");

            while (rs.next()) {

                    String username1 = rs.getString("username");
                    String password = rs.getString("password");
                    User user = new User( username, password);
                    return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(String username) {
        makeConnection();
        try {
            String rs = "DELETE FROM \"sep3\".customer WHERE username = '"+ username +"';";
            s.executeQuery(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

