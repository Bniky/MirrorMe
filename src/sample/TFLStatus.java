package sample;

import TransportLinks.London.LineStatus;
import TransportLinks.London.ServiceType;
import TransportLinks.London.TFLService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static sample.News.readUrl;

/**
 * Created by Nicholas on 19/05/2017.
 * Transport for London api status for TUBE and DLR
 */
public class TFLStatus {

    private static Map<String, String> TFL = new HashMap<String, String>();

    //Check if values equals empty String
    public static void setTFL(String key, String val){
        if(key == null || key.equals("") || val == null || val.equals("")){
            getTFL().put("", "Error");
        }else{
            getTFL().put(key, val);
        }
    }

    public static Map<String, String> getTFL(){

        return TFL;
    }

    public static void main(String [] args){

        //Read json array online url
        try {
            String json = readUrl("https://api.tfl.gov.uk/Line/Mode/tube%2Cdlr" +
                    "/Status?detail=true&app_id=eea5fc39&app_key=2cb56a998140923e0581b0b9d92e672e");

            Gson gson = new Gson();
            //Save fromjson to text in an array
            TFLService[] page = gson.fromJson(json, TFLService[].class);

            //Go through the data and collect Train name and service status
            for(TFLService eachService : page) {
                String trainName;
                String lineStatus;

                System.out.println("Train Lin " + eachService.getName());
                trainName = eachService.getName();

                for (LineStatus status : eachService.getLineStatuses()) {
                    System.out.println("Line status " + status.getStatusSeverityDescription());
                    lineStatus = status.getStatusSeverityDescription();
                    setTFL(trainName, lineStatus);
                }

                System.out.println(getTFL());
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error TFL API");
        }
    }
}
