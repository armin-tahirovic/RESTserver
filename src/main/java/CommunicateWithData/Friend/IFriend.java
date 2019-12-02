package CommunicateWithData.Friend;

import CommunicateWithData.User.User;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IFriend {

    @WebMethod
    String addFriend(String owner, String username);

    @WebMethod
    String delete(String owner, String username);
}
