package REST.Server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IUser {

    @WebMethod
    User opretBruger(User brugerData);

    @WebMethod
    User validereBruger(String email, String password);

    @WebMethod
    void skiftePassword(int id, String password);

    @WebMethod
    User read(int id);

    @WebMethod
    void sletteBruger(int id);
}
