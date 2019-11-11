package REST.Server;

import javax.xml.ws.Endpoint;

public class UserPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/WebServices/users", new UserDAO());
        System.out.println("Server running!");
    }
}
