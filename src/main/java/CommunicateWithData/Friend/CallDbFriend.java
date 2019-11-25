/*
package CommunicateWithData.Friend;

import java.sql.*;
import java.util.ArrayList;

public class CallDbFriend {

    ArrayList<Friend> friends = new ArrayList<>();
    Connection c = null;
    Statement s = null;

    private CallDbFriend() {
    }
    private static CallDbFriend callDbFriend = null;
    public static CallDbFriend CallDB() {
        if (callDbFriend == null) {
            callDbFriend = new CallDbFriend();
        }
        return callDbFriend;
    }

    public void makeConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sfp86nbb");
            s = c.createStatement();
        }
        catch (SQLException | ClassNotFoundException e ){
            e.printStackTrace();
        }
    }

    public ArrayList<Friend> getFriend(String owner) {
        makeConnection();

        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".'"+ owner +"';");

            while (rs.next()) {
                String username = rs.getString("brugernavn");
                friends.add(new Friend(username));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public void postFriend(String owner, String username) {
        makeConnection();

        try {
            String createUserSQL = "INSERT INTO \"poc\".'"+ owner +"' VALUES ( '"+ username +"');";
            s.executeQuery(createUserSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Friend getOneFriend(String owner, String username) {
        makeConnection();

        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".'"+ owner +"' WHERE username = '"+ username +"';");

            while (rs.next()) {
                String usernameDB = rs.getString("brugernavn");
                Friend friend = new Friend(usernameDB);
                return friend;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFriend(String owner, String username) {
        makeConnection();

        try {
            String rs = "DELETE FROM \"poc\".'"+ owner +"' WHERE username = '"+ username +"';";
            s.executeQuery(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/
