package REST.Server;

import java.sql.*;
import java.util.ArrayList;

public class CallDatabase {

    public ArrayList<User> getUser() {
        ArrayList<User> users = new ArrayList<>();
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");
            c.setAutoCommit(false);

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".bruger;");

            while (rs.next()) {
                int ID = rs.getInt("id");
                String username = rs.getString("brugernavn");
                String password = rs.getString("kode");
                users.add(new User(ID,username,password));

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User postUser(User brugerdata) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            System.out.println(brugerdata.getId()+brugerdata.getBrugernavn()+brugerdata.getPassword());
            String createUserSQL = "INSERT INTO \"poc\".bruger VALUES ('"+ brugerdata.getId() +"', '"+ brugerdata.getBrugernavn() +"', '"+ brugerdata.getPassword() +"');";
            s.executeQuery(createUserSQL);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return new User(brugerdata.getId(), brugerdata.getBrugernavn(), brugerdata.getPassword());
    }

    public void putPassword(int id, String password) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            System.out.println(id + password);
            String createUserSQL = "UPDATE \"poc\".bruger set kode = '"+ password +"' where id = '"+ id +"'";
            s.executeQuery(createUserSQL);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
