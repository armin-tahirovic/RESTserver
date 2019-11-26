/*
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
        return callDbFriend.getFriend(owner);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("friendRequest/{username}")
    public String sendFriendRequest(@PathParam("username") String username) {
        return callDbFriend.sendFriendRequest(username);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{owner}")
    public void addFriend(@PathParam("owner") String owner,String username) {
        callDbFriend.postFriend(owner, username);
    }

    @DELETE
    @Path("{username}")
    public void delete(@PathParam("username") String username, String owner) {
        callDbFriend.deleteFriend(owner, username);
    }
}
*/
