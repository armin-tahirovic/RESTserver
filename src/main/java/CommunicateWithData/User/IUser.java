package CommunicateWithData.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IUser {

    /*@WebMethod
    User createUser(String username, String password);*/

/*    @WebMethod
    User validereBruger(String brugernavn, String password);*/

    @WebMethod
    void changePassword(String username, String password);

    @WebMethod
    User read(String username);

    @WebMethod
    void delete(String username);
}
