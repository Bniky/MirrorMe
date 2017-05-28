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


    public static void main(String[] args) throws Exception {

        String json1 = News.readUrl("http://beniky.co.uk/MirrorMeExample.html");

        Gson gson = new Gson();
        Text page = gson.fromJson(json1, Text.class);

        //System.out.println(page.source.toUpperCase().replace('-', ' '));

        for (Message itextMessage : page.getMessages()) {
            System.out.println("    " + itextMessage.getName());
            System.out.println("    " + itextMessage.getMessage());

        }

    }
}
