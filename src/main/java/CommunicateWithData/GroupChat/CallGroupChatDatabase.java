package CommunicateWithData.GroupChat;


import CommunicateWithData.Chat.Chat;
import CommunicateWithData.Chat.ChatLog;

import java.security.acl.Group;
import java.sql.*;
import java.util.ArrayList;

public class CallGroupChatDatabase {
    private Connection c = null;
    private Statement s = null;

    private CallGroupChatDatabase() {
    }
    private static CallGroupChatDatabase chatDatabaseInstance = null;

    public static CallGroupChatDatabase CallDB() {
        if (chatDatabaseInstance == null) {
            chatDatabaseInstance = new CallGroupChatDatabase();
        }
        return chatDatabaseInstance;
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

    public ArrayList<GroupChat> getGroupChats(){
        ArrayList<GroupChat> allGroupChats = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".groupchat;");

            while (rs.next()) {
                int id = rs.getInt("groupChatID");
                String groupChatName = rs.getString("groupChatName");
                allGroupChats.add(new GroupChat(id, groupChatName));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGroupChats;
    }

    public String getChatIDByMembers(String count, String owner, String username){
        makeConnection();
        String chatID = "";
        try {
            System.out.println("count: " + count);
            System.out.println("owner: " + owner);
            System.out.println("username: " + username);
            ResultSet resultSet = s.executeQuery("select one.chatid from \"sep3\".chatmembers one  right join \"sep3\".chatmembers two on one.chatid = two.chatid where  one.chatid in (select chatid from \"sep3\".chatmembers group by chatid having  count(username) = " + count + " ) AND one.username = '" + owner + "' and two.username = '" + username + "';");

            while (resultSet.next()) {
                System.out.println("chatid is: " + resultSet.getInt("chatid"));
                int chatIDint = resultSet.getInt("chatid");
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

            ResultSet resultSet = s.executeQuery("INSERT INTO \"sep3\".chat(chatName) VALUES (" + chatname + ") RETURNING chatID;");

            long countLong = resultSet.getLong(1);
            return (int) countLong;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addChatLog(int chatID, String username, String message) {

        makeConnection();

        try {
            s.executeQuery("INSERT INTO \"sep3\".log VALUES (" + chatID + ", "+ username +", "+ message +")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<GroupChatLog> getChatLogs(int groupChatID) {
        ArrayList<GroupChatLog> allGroupChatLogs = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".log where chatID = '" + groupChatID +"';");

            while (rs.next()) {
                int id = rs.getInt("chatID");
                String username = rs.getString("username");
                String message = rs.getString("message");
                allGroupChatLogs.add(new GroupChatLog(id,username,message));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGroupChatLogs;
    }
}
