package REST.Client;

import REST.Server.User.User;
import REST.Server.User.UserDAO;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

public class WebServiceClient {

    public static void main(String[] args) throws Exception {
        /*URL url = new URL("http://localhost:8000/hello?wsdl");

        QName qName = new QName("http://Server.SOAP/","UserService");
        Service service = Service.create(url,qName);
        IUser user = service.getPort(IUser.class);

        System.out.println("client starts");
        user.opretBruger(new User(0, "j", "h"));*/

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target("http://localhost:8080/WebServices");

        GenericType<ArrayList<User>> userArrayListType = new GenericType<ArrayList<User>>(){};
        ArrayList<User> allUsers = target.path("users").request().accept(MediaType.APPLICATION_JSON).get(userArrayListType);
        for (User userList1: allUsers) {
            System.out.println(userList1.toString());
        }

        UserDAO userDAO = new UserDAO();
        target.path("users").request(MediaType.APPLICATION_JSON).post(Entity.json(userDAO.allUsers()));
        allUsers = target.path("users").request().accept(MediaType.APPLICATION_JSON).get(userArrayListType);
        for (User userList2: allUsers) {
            System.out.println(userList2.toString());
        }

/*        User user1 = new User(1,"123@via.dk","password");
        target.path("users").request(MediaType.APPLICATION_JSON).post(Entity.json(user1));
        allUsers = target.path("users").request().accept(MediaType.APPLICATION_JSON).get(userArrayListType);
        for (User userList2: allUsers) {
            System.out.println(userList2.toString());
        }*/

/*        target.path("users").path("1").request().delete();
        allUsers = target.path("users").request().accept(MediaType.APPLICATION_JSON).get(userArrayListType);
        for (User userList3: allUsers) {
            System.out.println(userList3);
        }*/
    }
}
