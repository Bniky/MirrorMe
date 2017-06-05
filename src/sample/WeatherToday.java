package sample;

import WeatherGs.Data;
import WeatherGs.Weather;
import com.fasterxml.jackson.databind.JavaType;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.List;
import com.google.gson.Gson;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import static sample.News.readUrl;

/**
 * Created by Nicholas on 16/05/2017.
 */
public class WeatherToday {

    private Long locationId;
    private  String icon;
    private String cityN;
    private String description;
    private float celsius;
    private String cels;

    public String getCels(){
        return cels + "°C";
    }

    public void setCelsius(float fahrenheit){
        if(fahrenheit == 32.0f){
            celsius = 0.0f;
            cels = Float.toString(celsius);

        }else{
            celsius = (fahrenheit - 32) * 5/9;
            cels = Float.toString(celsius);
            cels = cels.substring(0, 2);
        }
    }

    public String getDescription(){
        return description;
    }

    public void setDecription(String des){
        description = des;
    }

    public String getCityN(){
        return cityN;
    }

    public void setCityN(String cn){
        cityN = cn;
    }

    public String getIcon(){
        return icon;
    }

    public void setIcon(String s){
        icon = s;
    }


    public Long getLocationId (){
        return locationId;
    }

    public void setLocationId(Long l){
        if(l != 0) {
            locationId = l;
        }
    }

    static class JsonFile {
        List<Location> cities;    }

    static class Location {
        String name;
        String id;
        String country;
    }

    static class JsonFile2 {
        List<apiFile> info;    }

    static class apiFile {
        String description;
        String icon;
        String temp;
    }

    //Reads files in the json and get the city name
    public void getPersonLocationId(){
        LocateMyCity myCityD = new LocateMyCity();

        System.out.print(myCityD.getmyCityLocation() + " " + myCityD.getCountry());


        Gson gson = new Gson();
        try{
            JsonReader reader = new JsonReader(new FileReader("C:\\Users\\Nicholas\\IdeaProjects\\MirrorMe\\src\\citylist.json"));
            JsonFile page = gson.fromJson(reader, JsonFile.class);

            for (Location location : page.cities) {// Location of city and country Changed!!!
                if(location.name.equalsIgnoreCase(myCityD.getmyCityLocation()) && location.country.equalsIgnoreCase(myCityD.getCountry())) {
                    System.out.println("    " + location.id);
                    setLocationId(Long.parseLong(location.id));
                }
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public WeatherToday() {

        getPersonLocationId();
        // declaring object of "OpenWeatherMap" class
        OpenWeatherMap owm = new OpenWeatherMap("68bc905ffd10e22b761513734528f5de");

        try {
            // getting current weather data for the "London" city
            CurrentWeather cwd = owm.currentWeatherByCityCode(getLocationId());

            // checking data retrieval was successful or not
            if (cwd.isValid()) {

                // checking if city name is available
                if (cwd.hasCityName()) {
                    //printing city name from the retrieved data
                    System.out.println("City: " + cwd.getCityName());
                    setCityN(cwd.getCityName());
                }

                if (cwd.hasWeatherInstance()) {
                    setIcon(cwd.getWeatherInstance(0).getWeatherIconName());
                    setDecription(cwd.getWeatherInstance(0).getWeatherDescription());
                }

                if(cwd.hasMainInstance()){
                    setCelsius(cwd.getMainInstance().getMaxTemperature());
                    System.out.println(getCels());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String [] args){
        WeatherToday w = new WeatherToday();

    }
}

