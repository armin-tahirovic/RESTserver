package CommunicateWithData.GroupChat;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/groupchats")
@WebService
public class GroupGroupChatDAO implements IGroupChat {

    private CallGroupChatDatabase chatDatabase = CallGroupChatDatabase.CallDB();

    @GET
    public ArrayList<GroupChat> getChats(){return chatDatabase.getGroupChats();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{count}/{owner}/{username}")
    public String getChatIDByMembers(@PathParam("count") String count, @PathParam("owner")String owner, @PathParam("username")String username){

        return chatDatabase.getChatIDByMembers(count, owner, username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{chatID}")
    public ArrayList<GroupChatLog> getChatLogs(@PathParam("chatID") String chatID){
        int result = Integer.parseInt(chatID);
        return chatDatabase.getChatLogs(result);
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int addChat(String chatName) {
        return chatDatabase.addChat(chatName);
    }

    @POST
    @Path("/{chatID}/{username}/{message}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addChatLog(@PathParam("chatID") int chatID, @PathParam("username")String username, @PathParam("message")String message) {
        chatDatabase.addChatLog(chatID, username, message);
    }
}
