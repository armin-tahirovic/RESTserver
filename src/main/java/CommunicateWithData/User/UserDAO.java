package CommunicateWithData.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/users")
@WebService
public class UserDAO implements IUser {

    private CallDatabase callDatabase = CallDatabase.CallDB();

    public UserDAO() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> allUsers() {
        return callDatabase.getUser();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public User createUser( User user) {
        callDatabase.postUser(user);
        return user;
    }

/*    @GET
    @Path("/validate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User validereBruger(@PathParam("id") String brugernavn, String password) {
        for(User user: userList) {
            if (user.getPassword().equals(password) && user.getBrugernavn().equals(brugernavn))
                return user;
        }
        return null;
    }*/


    @PUT
    @Path("/changePW/{username}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void changePassword(@PathParam("username") String username, String password) {
        callDatabase.putPassword(username,password);
    }

    @GET
    @Path("{username}")
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
