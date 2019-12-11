package CommunicateWithData.Chat;

public class GroupChat {

    int groupID;
    String username;
    boolean admin;

    public GroupChat(int groupID, String username, boolean admin) {
        this.groupID = groupID;
        this.username = username;
        this.admin = admin;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
