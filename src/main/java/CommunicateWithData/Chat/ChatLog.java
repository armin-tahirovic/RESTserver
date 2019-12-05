package CommunicateWithData.Chat;

import java.util.Date;

public class ChatLog {
    private int chatID;
    String username;
    String message;


    public ChatLog(int chatID, String username, String message) {
        this.chatID = chatID;
        this.username = username;
        this.message = message;

    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
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
