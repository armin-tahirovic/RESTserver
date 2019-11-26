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

    public ArrayList<Friend> getFriend(String owner) {
        ArrayList<Friend> friends = new ArrayList<>();
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\"."+ owner +";");

            while (rs.next()) {
                String username = rs.getString("brugernavn");
                friends.add(new Friend(username));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public String sendFriendRequest(String username) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".bruger WHERE brugernavn = '"+ username +"';");

            while (rs.next()) {
                String user = rs.getString("brugernavn");
                return user;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void postFriend(String owner, String username) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");

            s = c.createStatement();
            String createUserSQL = "INSERT INTO \"poc\"."+ owner +" VALUES ( '"+ username +"');";
            s.executeQuery(createUserSQL);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFriend(String owner, String username) {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sfp86nbb");

            s = c.createStatement();
            String rs = "DELETE FROM \"poc\"."+ owner +" WHERE username = '"+ username +"';";
            s.executeQuery(rs);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
