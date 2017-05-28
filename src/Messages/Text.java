package Messages;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicholas on 22/05/2017.
 */

public class Text {

    @SerializedName("Messages")
    @Expose
    private List<Message> messages = null;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}