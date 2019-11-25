package CommunicateWithData.User;

import java.sql.*;
import java.util.ArrayList;

public class CallDatabase {

    private CallDatabase() {
    }
    private static CallDatabase callDatabase = null;
    public static CallDatabase CallDB() {
        if (callDatabase == null) {
            callDatabase = new CallDatabase();
        }
        return callDatabase;
    }

    public ArrayList<User> getUser() {
        ArrayList<User> users = new ArrayList<>();
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".bruger;");

            while (rs.next()) {

                String username = rs.getString("brugernavn");
                String password = rs.getString("kode");
                users.add(new User(username,password));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User postUser(User userdata) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            String createUserSQL = "INSERT INTO \"poc\".bruger VALUES ('"+ userdata.getUsername() +"', '"+ userdata.getPassword() +"');";
            s.executeQuery(createUserSQL);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return new User( userdata.getUsername(), userdata.getPassword());
    }

    public void putPassword(String username, String password) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            System.out.println(username + password);
            String createUserSQL = "UPDATE \"poc\".bruger set kode = '"+ password +"' where username = '"+ username +"'";
            s.executeQuery(createUserSQL);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public User getOneUser(String username) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sfp86nbb");

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".bruger WHERE username = '"+ username +"';");

            while (rs.next()) {

                    String username1 = rs.getString("brugernavn");
                    String password = rs.getString("kode");
                    User user = new User( username, password);
                    return user;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(String username) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sfp86nbb");

            s = c.createStatement();
            String rs = "DELETE FROM \"poc\".bruger WHERE username = '"+ username +"';";
            s.executeQuery(rs);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
