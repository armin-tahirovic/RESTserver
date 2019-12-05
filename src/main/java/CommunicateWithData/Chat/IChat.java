package CommunicateWithData.Chat;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IChat {

    @WebMethod
    ArrayList<Chat> getChats();

    @WebMethod
    ArrayList<ChatLog> getChatLogs(int chatID);

    @WebMethod
    int addChat(String chatName);

    @WebMethod
    void addChatLog(int id, String username, String message);

}
