package CommunicateWithData.GroupChat;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IGroupChat {

    @WebMethod
    ArrayList<GroupChat> getChats();

    @WebMethod
    ArrayList<GroupChatLog> getChatLogs(String chatID);

    @WebMethod
    String getChatIDByMembers(String count, String owner, String username);

    @WebMethod
    int addChat(String chatName);

    @WebMethod
    void addChatLog(int id, String username, String message);

}
