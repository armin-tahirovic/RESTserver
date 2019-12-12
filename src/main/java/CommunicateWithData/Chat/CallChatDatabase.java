package CommunicateWithData.Chat;


import java.sql.*;
import java.util.ArrayList;

public class CallChatDatabase {
    private Connection c = null;
    private Statement s = null;

    private CallChatDatabase() {
    }
    private static CallChatDatabase chatDatabaseInstance = null;

    public static CallChatDatabase CallDB() {
        if (chatDatabaseInstance == null) {
            chatDatabaseInstance = new CallChatDatabase();
        }
        return chatDatabaseInstance;
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

    public ArrayList<Chat> getChats(){
        ArrayList<Chat> allChats = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".chat;");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String chatName = rs.getString("chatName");
                allChats.add(new Chat(id, chatName));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allChats;
    }

    public ArrayList<GroupChat> getGroupChat(String owner){
        ArrayList<GroupChat> allChats = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".chatmembers WHERE username = '"+ owner +"';");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String username = rs.getString("username");
                boolean admin = rs.getBoolean("admin");
                allChats.add(new GroupChat(id,username,admin));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allChats;
    }

    public ArrayList<GroupChat> getGroupMembers(int id){
        ArrayList<GroupChat> allChats = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".chatmembers WHERE id = '"+ id +"';");

            while (rs.next()) {
                int member = rs.getInt("ID");
                String username = rs.getString("username");
                boolean admin = rs.getBoolean("admin");
                allChats.add(new GroupChat(member,username,admin));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allChats;
    }

    public String getChatIDByMembers(String count, String owner, String username){
        makeConnection();
        String chatID = "";
        try {
            System.out.println("count: " + count);
            System.out.println("owner: " + owner);
            System.out.println("username: " + username);
            ResultSet resultSet = s.executeQuery("select one.ID from \"sep3\".chatmembers one  right join \"sep3\".chatmembers two on one.ID = two.ID where  one.ID in (select ID from \"sep3\".chatmembers group by ID having  count(username) = " + count + " ) AND one.username = '" + owner + "' and two.username = '" + username + "';");

            while (resultSet.next()) {
                System.out.println("chatid is: " + resultSet.getInt("ID"));
                int chatIDint = resultSet.getInt("ID");
                chatID = String.valueOf(chatIDint);

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatID;
    }

    public int addChat(String chatname) {

        makeConnection();

        try {

            ResultSet resultSet = s.executeQuery("INSERT INTO \"sep3\".chat(chatName) VALUES (" + chatname + ") RETURNING ID;");

            long countLong = resultSet.getLong(1);
            return (int) countLong;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addChatLog(int chatID, String username, String message) {
        System.out.println("Adding chat log");
        makeConnection();

        try {
            s.executeUpdate("INSERT INTO \"sep3\".log VALUES (" + chatID + ", '"+ username +"', '"+ message +"', now());");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String addMember(int id, String username) {
        System.out.println("Adding member");
        makeConnection();

        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".chatmembers WHERE id = '"+ id +"';");

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    return username + " already member of chat";
                }
            }
            s.executeUpdate("INSERT INTO \"sep3\".chatmembers VALUES (" + id + ", '"+ username +"', '"+ false +"';");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username + " added to chat";
    }

    public String removeMember(int id, String username) {
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".chatmembers WHERE id = '"+ id +"';");

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    s.executeUpdate("DELETE FROM \"sep3\".chatmembers WHERE id = '"+ id +"' AND username = '" + username + "'; ");

                    return username + " removed from chat";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "You are not able to remove " + username;
    }


    public ArrayList<ChatLog> getChatLogs(int chatID) {
        ArrayList<ChatLog> allChatLogs = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".log where ID = '" + chatID +"';");

            while (rs.next()) {
                int id = rs.getInt("chatID");
                String username = rs.getString("username");
                String message = rs.getString("message");
                allChatLogs.add(new ChatLog(id, username,message));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allChatLogs;
    }
}
