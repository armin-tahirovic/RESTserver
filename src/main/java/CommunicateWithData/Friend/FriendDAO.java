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
    public ArrayList<Friend> allFriends(String owner) {
        return callDbFriend.getFriend(owner);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{owner}")
    public void addFriend(@PathParam("owner")String owner,Friend friendData) {
        callDbFriend.postFriend(owner, friendData.getUsername());
    }

    @DELETE
    @Path("{username}")
    public void delete(@PathParam("username") String username, String owner) {
        callDbFriend.deleteFriend(owner, username);
    }
}
*/
