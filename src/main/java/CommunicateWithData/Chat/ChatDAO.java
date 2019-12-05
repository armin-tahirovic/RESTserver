package CommunicateWithData.Chat;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/chats")
@WebService
public class ChatDAO implements IChat {

    private CallChatDatabase chatDatabase = CallChatDatabase.CallDB();

    @GET
    public ArrayList<Chat> getChats(){
        return chatDatabase.getChats();
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
    public ArrayList<ChatLog> getChatLogs(@PathParam("chatID") String chatID){
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
