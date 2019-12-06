package CommunicateWithData.GroupChat;

public class GroupChat {
    private int groupChatID;
    private String groupChatName;

    public GroupChat(int groupChatID, String groupChatName) {
        this.groupChatID = groupChatID;
        this.groupChatName = groupChatName;
    }



    public int getGroupChatID() { return groupChatID;
    }

    public void setGroupChatID(int chatID) {
        this.groupChatID = groupChatID;
    }

    public String getGroupChatName() {
        return groupChatName;
    }

    public void setGroupChatName(String chatName) {
        this.groupChatName = groupChatName;
    }
}
