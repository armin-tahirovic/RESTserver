package CommunicateWithData.GroupChat;

public class GroupChatLog {
    private int groupChatID;
    String username;
    String message;


    public GroupChatLog(int chatID, String username, String message) {
        this.groupChatID = chatID;
        this.username = username;
        this.message = message;

    }

    public int getGroupChatID() {
        return groupChatID;
    }

    public void setGroupChatID(int groupChatID) {
        this.groupChatID = groupChatID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
