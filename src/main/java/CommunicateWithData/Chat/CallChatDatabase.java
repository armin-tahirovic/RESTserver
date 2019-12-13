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
                int id = rs.getInt("chatID");
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
                int id = rs.getInt("chatid");
                String username = rs.getString("username");
                boolean admin = rs.getBoolean("admin");
                allChats.add(new GroupChat(id,username,admin));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allChats;
    }

    public ArrayList<GroupChat> getGroupMembers(int chatID){
        ArrayList<GroupChat> allMembers = new ArrayList<>();
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".chatmembers WHERE chatid = '"+ chatID +"';");

            while (rs.next()) {
                int member = rs.getInt("chatid");
                String username = rs.getString("username");
                boolean admin = rs.getBoolean("admin");
                allMembers.add(new GroupChat(member,username,admin));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allMembers;
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
                    return chatID;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("result set is empty");
        int newChatID = addChat("new chat");
        System.out.println(" NEW Chat ID is: " + newChatID);
        addChatMember(newChatID, owner, true);
        addChatMember(newChatID, username, false);
        chatID = String.valueOf(newChatID);
        System.out.println("The final chatID: " + chatID);
        return chatID;
    }

    public void addChatMember(int chatID, String username, boolean admin){
        try {
            s.executeUpdate("INSERT INTO \"sep3\".chatmembers VALUES (" + chatID + ", '"+ username +"', "+ admin +");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addChat(String chatname) {

        makeConnection();

        try {
            ResultSet resultSet = s.executeQuery("INSERT INTO \"sep3\".chat(chatName) VALUES ('" + chatname + "') RETURNING chatid;");
            while (resultSet.next()) {
                long countLong = resultSet.getLong("chatid");
                return (int) countLong;
            }


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

    public String addMember(int chatID, String username, boolean admin) {
        System.out.println("Adding member");
        makeConnection();

        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".chatmembers WHERE chatid = '"+ chatID +"';");

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    return username + " already member of chat";
                }
            }

            System.out.println("ID " + chatID);
            System.out.println("Username " + username);
            System.out.println("Admin " + admin);
            s.executeUpdate("INSERT INTO \"sep3\".chatmembers VALUES (" + chatID + ", '"+ username +"', '"+ admin +"');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username + " added to chat";
    }

    public String removeMember(int chatID, String username) {
        makeConnection();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".chatmembers WHERE chatid = '"+ chatID +"';");

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    s.executeUpdate("DELETE FROM \"sep3\".chatmembers WHERE chatid = '"+ chatID +"' AND username = '" + username + "' AND admin = '"+ false +"'");

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
            ResultSet rs = s.executeQuery("SELECT * FROM \"sep3\".log where chatID = '" + chatID +"';");

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
