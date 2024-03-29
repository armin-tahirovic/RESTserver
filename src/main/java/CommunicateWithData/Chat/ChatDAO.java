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
    @Path("GroupChat/{owner}")
    public ArrayList<GroupChat> getGroupChat(@PathParam("owner") String owner){
        return chatDatabase.getGroupChat(owner);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("GroupMembers/{id}")
    public ArrayList<GroupChat> getGroupMembers(@PathParam("id") int id){
        return chatDatabase.getGroupMembers(id);
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("CreateChat/{chatname}")
    public int addChat(@PathParam("chatname") String chatName) {
        return chatDatabase.addChat(chatName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addChatLog(ChatLog log) {
        System.out.println("Adding Log");
       chatDatabase.addChatLog(log.getChatID(),log.getUsername(),log.getMessage());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("AddMember/{id}/{username}/{admin}")
    public String addMember(@PathParam("id") int id, @PathParam("username") String username, @PathParam("admin") boolean admin) {
        return chatDatabase.addMember(id, username, admin);
    }

    @DELETE
    @Path("RemoveMember/{id}/{username}/{admin}")
    public String removeMember(@PathParam("id") int id, @PathParam("username") String username) {
        return chatDatabase.removeMember(id, username);
    }
}
