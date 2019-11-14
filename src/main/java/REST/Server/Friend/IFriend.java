package REST.Server.Friend;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IFriend {

    @WebMethod
    Friend addFriend(Friend friendData);

    @WebMethod
    Friend read(int id);

    @WebMethod
    void delete(int id);
}
