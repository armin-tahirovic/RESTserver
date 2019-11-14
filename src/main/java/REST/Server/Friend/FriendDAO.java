package REST.Server.Friend;

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
    public ArrayList<Friend> allUsers() {
        return callDbFriend.getFriend();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Friend addFriend(Friend friendData) {
        ArrayList<Friend> userList = callDbFriend.getFriend();
        int id = userList.size();
        Friend friend = new Friend(id, friendData.getBrugernavn());
        callDbFriend.postFriend(friend);
        return friend;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Friend read(@PathParam("id") int id) {
        return callDbFriend.getOneFriend(id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        callDbFriend.deleteFriend(id);
    }
}
