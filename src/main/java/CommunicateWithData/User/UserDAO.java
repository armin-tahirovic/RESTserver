package CommunicateWithData.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/users")
@WebService
public class UserDAO implements IUser {

    private CallDatabase callDatabase = CallDatabase.CallDB();
    private String username;

    public UserDAO() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> allUsers() {
        return callDatabase.getUser();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(User user) {
        return callDatabase.postUser(user);
    }

    @PUT
    @Path("/changePW/{username}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void changePassword(@PathParam("username") String username, String password) {
        callDatabase.putPassword(username, password);
    }


    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User read(@PathParam("username") String username)
    {
        return callDatabase.getOneUser(username);
    }

    @DELETE
    @Path("{username}")
    public void delete(@PathParam("username") String username) {
        callDatabase.deleteUser(username);
    }
}
