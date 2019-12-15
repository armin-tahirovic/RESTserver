package CommunicateWithData.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IUser {

    @WebMethod
    String createUser(User user);

    @WebMethod
    void changePassword(String username, String password);

    @WebMethod
    User read(String username);

    @WebMethod
    void delete(String username);
}
