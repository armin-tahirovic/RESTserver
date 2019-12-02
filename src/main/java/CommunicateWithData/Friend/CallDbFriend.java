package CommunicateWithData.Friend;

import java.sql.*;
import java.util.ArrayList;

public class CallDbFriend {

    private Connection c = null;
    private Statement s = null;

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

    public ArrayList<Friend> allFriends(String owner) {
        ArrayList<Friend> friends = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\"."+ owner +";");

            while (rs.next()) {
                String username = rs.getString("brugernavn");
                friends.add(new Friend(username));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public String checkUser(String owner, String username) {
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".customer WHERE username = '"+ username +"';");

            if (rs != null) {
                s.executeQuery("INSERT INTO \"sep3\"."+ owner +" VALUES ("+ username +", "+ false +", "+ false +", "+ false +")");
                return "Valid";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String friendRequest(String owner, String username) {
        makeConnection();
        try {

            // Skal sætte Request boolean til true
            ResultSet rs = s.executeQuery("UPDATE \"poc\"."+ owner +" SET request = "+ true +" WHERE username = "+ username +"");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Done";
    }

    public String postFriend(String owner, String username) {
        makeConnection();
        try {
            String createUserSQL = "UPDATE \"poc\"."+ owner +" SET friend = "+ true +" WHERE username = "+ username +"";
            s.executeQuery(createUserSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Friend added";
    }

    public String delete(String owner, String username) {
        makeConnection();
        try {
            String rs = "DELETE FROM \"poc\"."+ owner +" WHERE username = '"+ username +"';";
            s.executeQuery(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User deleted";
    }
}
