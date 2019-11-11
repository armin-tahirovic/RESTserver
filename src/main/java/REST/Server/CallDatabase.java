package REST.Server;

import java.sql.*;
import java.util.ArrayList;

public class CallDatabase {

    public ArrayList<User> getUser() {
        ArrayList<User> users = new ArrayList<>();
        Connection c = null;
        Statement s = null;

        try {
            System.out.println("Connecting to DB");
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");
            c.setAutoCommit(false);
            System.out.println("DB running!");

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".bruger;");

            while (rs.next()) {
                int ID = rs.getInt(1);
                String username = rs.getString("brugernavn");
                String password = rs.getString("kode");
                users.add(new User(ID,username,password));

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
