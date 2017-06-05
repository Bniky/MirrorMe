package sample;

/**
 * Created by Nicholas on 16/05/2017.
 */

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;

import java.net.*;
import java.io.*;

public class LocateMyCity {

    private String myCityLocation;

    private String country;

    public String getCountry() {
        return country;
    }

    public String getmyCityLocation(){
        return myCityLocation;
    }

    public LocateMyCity() {
        // A File object pointing to your GeoIP2 or GeoLite2 database
        File database = new File("src\\GeoLite2-City_20170502\\GeoLite2-City.mmdb");

        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            System.out.println(ip);

            // This creates the DatabaseReader object, which should be reused across
            // lookups.
            DatabaseReader reader = new DatabaseReader.Builder(database).build();

            InetAddress ipAddress = InetAddress.getByName(ip);

            // Replace "city" with the appropriate method for your database, e.g.,
            // "country".
            CityResponse response = reader.city(ipAddress);

            City city = response.getCity();
            System.out.println(city.getName()); // 'Minneapolis'
            this.myCityLocation = city.getName();

            Country country = response.getCountry();
            System.out.println(country.getIsoCode());            // 'GB'
            this.country = country.getIsoCode();

            System.out.println(country.getName());               // 'United Kindom'

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Tracing IP E");
        }
    }
}
