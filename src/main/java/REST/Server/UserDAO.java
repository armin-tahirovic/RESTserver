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

    private static ArrayList<User> userList = new ArrayList<User>();

    public UserDAO() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> alleBrugere() {
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sfp86nbb");
            c.setAutoCommit(false);

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"poc\".bruger;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("brugernavn");
                String password = rs.getString("kode");
                userList.add(new User(id,username,password));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public User opretBruger(User brugerData) {
        String email = brugerData.getEmail();
        String password = brugerData.getPassword();
        int id = userList.size();
        User user = new User(id, email, password);
        userList.add(user);
        return user;
    }

    @GET
    @Path("validate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User validereBruger(@PathParam("id") String email, String password) {
        for(User user: userList) {
            if (user.getPassword().equals(password) && user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    @PUT
    @Path("changePW/{id}")
    public void skiftePassword(@PathParam("id") int id, String password) {
        userList.get(id).setPassword(password);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User read(@PathParam("id") int id)
    {
        return userList.get(id);
    }

    @DELETE
    @Path("{id}")
    public void sletteBruger(@PathParam("id") int id) {
        User user = read(id);
        userList.remove(user);
    }
}
