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
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
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

    public String checkUser(String username) {
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".customer;");

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    return "Valid";
                }
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return "User doesn't exist";
    }

    public String friendRequest(String owner, String username) {
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\"."+ owner +";");

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    return "Request not send. Already friends";
                }
            }

            s.executeUpdate("INSERT INTO \"sep3\"." + username + " VALUES ('" + owner + "', " + false + ", " + false + ", " + false + ");");
            s.execute("UPDATE \"sep3\"."+ username +" SET request = "+ true +" WHERE username = '"+ owner +"';");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "You request have been send";
    }

    public ArrayList<Friend> getFriendRequest(String owner) {
        ArrayList<Friend> friendRequest = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\"."+ owner +" WHERE request = "+ true +";");

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
            // Tilføjer senderen til modtagerens tabel
            s.executeUpdate("INSERT INTO \"sep3\"." + username + " VALUES ('" + owner + "', " + true + ", " + false + ", " + false + ");");

            // Opdatere senderens tabel
            s.executeUpdate("UPDATE \"sep3\"."+ owner +" SET friend = " + true + " WHERE username = '" + username + "';");
            s.executeUpdate("UPDATE \"sep3\"."+ owner +" SET request = " + false + " WHERE username = '" + username + "';");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Friend added";
    }

    public String reject(String owner, String username) {
        makeConnection();
        try {

            String sql = "DELETE FROM \"sep3\"." + owner + " WHERE username = '"+ username +"'; ";
            s.executeUpdate(sql);

            System.out.println(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User rejected";
    }

    public String delete(String owner, String username) {
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\"."+ owner +";");

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    s.executeUpdate("DELETE FROM \"sep3\"." + owner + " WHERE username = '" + username + "'; ");
                    s.executeUpdate("DELETE FROM \"sep3\"." + username + " WHERE username = '" + owner + "'; ");

                    return "User deleted";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "You are not friends with " + username;
    }
}
