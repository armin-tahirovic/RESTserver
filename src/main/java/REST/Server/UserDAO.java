package REST.Server;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;


@Path("/users")
@WebService
public class UserDAO implements IUser {

    private static ArrayList<User> userList = new ArrayList<>();

    public UserDAO() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> allUsers() {
        CallDatabase callDatabase = new CallDatabase();
        userList = callDatabase.getUser();
        return userList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User brugerData) {
        CallDatabase callDatabase = new CallDatabase();
        userList = callDatabase.getUser();
        int id = userList.size() + 1;
        User user = new User(id, brugerData.getBrugernavn(),brugerData.getPassword());
        callDatabase.postUser(user);
        return user;
    }

    @GET
    @Path("/validate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User validereBruger(@PathParam("id") String brugernavn, String password) {
        for(User user: userList) {
            if (user.getPassword().equals(password) && user.getBrugernavn().equals(brugernavn))
                return user;
        }
        return null;
    }


    @PUT
    @Path("/changePW/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void changePassword(@PathParam("id") int id, String password) {
        CallDatabase callDatabase = new CallDatabase();
        callDatabase.putPassword(id,password);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User read(@PathParam("id") int id)
    {
        return userList.get(id);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") int id) {
        User user = read(id);
        userList.remove(user);
    }
}
