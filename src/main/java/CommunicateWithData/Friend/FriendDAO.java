package CommunicateWithData.Friend;

import CommunicateWithData.User.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;



@Path("/friends")
@WebService
public class FriendDAO implements IFriend {

    private CallDbFriend callDbFriend = CallDbFriend.CallDB();
    public FriendDAO() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{owner}")
    public ArrayList<Friend> allFriends(@PathParam("owner") String owner) {
        return callDbFriend.allFriends(owner);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("checkUser/{owner}/{username}")
    public String checkUser(@PathParam("owner") String owner, @PathParam("username") String username) {
        return callDbFriend.checkUser(owner, username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("friendRequest/{owner}/{username}")
    public String friendRequest(@PathParam("owner") String owner, @PathParam("username") String username) {
        return callDbFriend.friendRequest(owner, username);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{owner}")
    public String addFriend(@PathParam("owner") String owner,String username) {
        return callDbFriend.postFriend(owner, username);
    }

    @DELETE
    @Path("{owner}, {username}")
    public String delete(@PathParam("owner") String owner, @PathParam("username") String username) {
         return callDbFriend.delete(owner, username);
    }
}