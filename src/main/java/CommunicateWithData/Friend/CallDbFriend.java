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
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\"."+ owner +" WHERE friend = "+ true +";");

            while (rs.next()) {
                String username = rs.getString("username");
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
                try {
                    s.executeUpdate("INSERT INTO \"sep3\"."+ username +" VALUES ('"+ owner +"', "+ false +", "+ false +", "+ false +")");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

            String updateRequest = ("UPDATE \"sep3\"."+ username +" SET request = "+ true +" WHERE username = '"+ owner +"'");
            s.execute(updateRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Friend request send";
    }

    public ArrayList<Friend> getFriendRequest(String owner) {
        ArrayList<Friend> friendRequest = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\"."+ owner +" WHERE request = "+ true +"");

            while (rs.next()) {
                String username = rs.getString("username");
                friendRequest.add(new Friend(username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendRequest;
    }

    public String postFriend(String owner, String username) {
        makeConnection();
        try {

                s.executeUpdate("INSERT INTO \"sep3\"." + username + " VALUES ('" + owner + "', " + true + ", " + false + ", " + false + ")");

            System.out.println("Owner: " + owner);
            System.out.println("Username: " + username);

                System.out.println("STEP 1");
                s.executeUpdate("UPDATE \"sep3\"."+ owner +" SET friend = "+ true +" WHERE username = '"+ username +"'");

                System.out.println("STEP 2");
                s.executeUpdate("UPDATE \"sep3\"."+ owner +" SET request = "+ false +" WHERE username = '"+ username +"'");

            System.out.println("Burde have tilfojet ven");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Friend added";
    }

    public String delete(String owner, String username) {
        makeConnection();
        try {

            System.out.println("Owner " + owner);
            System.out.println("Username " + username);

            String sql = "DELETE FROM \"sep3\"." + owner + " WHERE username = '"+ username +"' ";
            s.executeUpdate(sql);

            System.out.println(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User removed";
    }
}
