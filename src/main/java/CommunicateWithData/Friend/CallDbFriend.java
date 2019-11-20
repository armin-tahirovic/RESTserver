package CommunicateWithData.Friend;

import java.sql.*;
import java.util.ArrayList;

public class CallDbFriend {

    private CallDbFriend() {
    }
    private static CallDbFriend callDbFriend = null;
    public static CallDbFriend CallDB() {
        if (callDbFriend == null) {
            callDbFriend = new CallDbFriend();
        }
        return callDbFriend;
    }

    public ArrayList<Friend> getFriend() {
        ArrayList<Friend> friends = new ArrayList<>();
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".venner;");

            while (rs.next()) {
                int ID = rs.getInt("id");
                String username = rs.getString("brugernavn");
                friends.add(new Friend(ID,username));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public Friend postFriend(Friend friendData) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            String createUserSQL = "INSERT INTO \"poc\".venner VALUES ('"+ friendData.getId() +"', '"+ friendData.getBrugernavn() +"');";
            s.executeQuery(createUserSQL);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return new Friend(friendData.getId(), friendData.getBrugernavn());
    }

    public Friend getOneFriend(int id) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sfp86nbb");

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".venner WHERE id = '"+ id +"';");

            while (rs.next()) {
                int ID = rs.getInt("id");
                String username = rs.getString("brugernavn");
                Friend friend = new Friend(ID, username);
                return friend;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFriend(int id) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sfp86nbb");

            s = c.createStatement();
            String rs = "DELETE FROM \"poc\".venner WHERE id = '"+ id +"';";
            s.executeQuery(rs);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
