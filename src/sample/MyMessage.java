package sample;

import Messages.Message;
import Messages.Text;
import WeatherGs.Sys;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Nicholas on 20/05/2017.
 */
public class MyMessage {


    private String message;

    public String getMessage(){
        return message;
    }

    public void setMessage(String mess){

        this.message = mess;
    }

    public MyMessage() {

        try {
            String json1 = News.readUrl("http://beniky.co.uk/MirrorMeExample.html");

            Gson gson = new Gson();
            Text page = gson.fromJson(json1, Text.class);

            //System.out.println(page.source.toUpperCase().replace('-', ' '));

            for (Message itextMessage : page.getMessages()) {
                System.out.println("    " + itextMessage.getName());
                this.message = itextMessage.getMessage();

            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ii");
        }
    }
}
