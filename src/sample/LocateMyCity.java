package sample;

/**
 * Created by Nicholas on 16/05/2017.
 */

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        try {
            Path appDirectory = Paths.get(System.getProperty("user.home"), ".MirrorMe");
            Path databaseFile = appDirectory.resolve("GeoList2-City.mmdb");

            if (! Files.exists(databaseFile)) {
                try {
                    // create the app directory if it doesn't already exist:
                    Files.createDirectories(appDirectory);

                    InputStream defaultDatabase = getClass().getClassLoader().getResourceAsStream("GeoLite2-City.mmdb");
                    Files.copy(defaultDatabase, databaseFile);
                } catch (IOException exc) {
                    // handle exception here, e.g. if application can run without db,
                    // set flag indicating it must run in non-db mode
                    // otherwise this is probably a fatal exception, show message and exit...
                    exc.printStackTrace();
                }
            }


            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            System.out.println(ip);

            // This creates the DatabaseReader object, which should be reused across
            // lookups.
            DatabaseReader reader = new DatabaseReader.Builder(databaseFile.toFile()).build();

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
        }
    }
}
