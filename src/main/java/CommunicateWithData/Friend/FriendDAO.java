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
    @Path("/{owner}")
    public ArrayList<Friend> allFriends(@PathParam("owner") String owner) {
        return callDbFriend.allFriends(owner);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("checkUser/{username}")
    public String checkUser(@PathParam("username") String username) {
        return callDbFriend.checkUser(username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("friendRequest/{owner}/{username}")
    public String friendRequest(@PathParam("owner") String owner, @PathParam("username") String username) {
        return callDbFriend.friendRequest(owner, username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("getFriendRequest/{owner}")
    public ArrayList<Friend> getFriendRequest(@PathParam("owner") String owner) {
        return callDbFriend.getFriendRequest(owner);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{owner}")
    public String addFriend(@PathParam("owner") String owner,String username) {
        return callDbFriend.postFriend(owner, username);
    }

    @DELETE
    @Path("rejectUser/{owner}/{username}")
    public String rejectUser(@PathParam("owner") String owner, @PathParam("username") String username) {
        return callDbFriend.reject(owner, username);
    }

    @DELETE
    @Path("deleteUser/{owner}/{username}")
    public String delete(@PathParam("owner") String owner, @PathParam("username") String username) {
         return callDbFriend.delete(owner, username);
    }
}