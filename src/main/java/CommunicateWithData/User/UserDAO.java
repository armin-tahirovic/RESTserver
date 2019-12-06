package CommunicateWithData.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/users")
@WebService
public class UserDAO implements IUser {

    private CallDatabase callDatabase = CallDatabase.CallDB();

    public UserDAO() {}

    @GET
    @Produces(APPLICATION_JSON)
    public ArrayList<User> allUsers() {
        return callDatabase.getUser();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("createuser")
    public void createUser( User user) {
        callDatabase.postUser(user);
        System.out.println(user);

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
    @Produces(APPLICATION_JSON)
    public User read(@PathParam("username") String username)
    {
        return callDatabase.getOneUser(username);
    }

    @DELETE
    @Path("{username}")
    public void delete(@PathParam("username") String username) {
        callDatabase.deleteUser(username);
    }

    @DELETE
    @Path("{username}{chatID}")
    public void deleteGroupMember(@PathParam("username") String username, int chatID) { callDatabase.deleteGrupMember(username,chatID);
    }
}
