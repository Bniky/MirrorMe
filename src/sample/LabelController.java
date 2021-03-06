package sample;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.util.Map;


/**
 * Created by Nicholas on 01/06/2017.
 */
public class LabelController {

    @FXML
    private TextArea lblN;

    @FXML
    private Label lblTime;

    @FXML
    private Label loc;

    @FXML
    private Label trainUpdate;

    @FXML
    private Label monthDay;

    @FXML
    private Label TFLline;

    @FXML
    private Label BBCL;

    @FXML
    private ImageView weatherIconId;

    private static String i;

    public void getAndSetData(){
        setTime();
        setNews();
        setLocation();
        setTrainStatus();
    }

    public void setTime(){
        try {
            LocalTime watch = LocalTime.now();
            DateTimeFormatter shortTime = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
            i = shortTime.format(watch);

            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM, dd");
            String formatDate = now.format(formatter);

            System.out.println("After : " + formatDate);

            lblTime.setText(i);
            monthDay.setText(formatDate);

        }catch(Exception e){
            lblTime.setText("Error T");
        }
    }

    public void setNews(){

        BBCL.setTextFill(Color.web("#ff270f"));

        try {
            String news = "";

            for(String n: new News().getHeadLine()){
                news += n + "\n";
            }

            //Scroll bar in the textArea
            ScrollBar scrollBarv = (ScrollBar)lblN.lookup(".scroll-bar:vertical");
            //Hide scrollbar
            scrollBarv.setDisable(true);
            lblN.setWrapText(true);

            //New information.
            lblN.setText(news);
            lblN.appendText("\n"+ "\n"+ "\n"+ "\n");
            //Automatic scrolling function
            slowScrollToBottom(scrollBarv);
        }catch(Exception e){
            lblN.setText("Error News");
        }
    }

    static void slowScrollToBottom(ScrollBar scrollPane) {
        scrollPane.setValue(1.5);
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(7),
                        new KeyValue(scrollPane.valueProperty(), 0)));
        animation.play();
    }

    public void setLocation(){
        WeatherToday wT = new WeatherToday();

        Path appDirectory = Paths.get(System.getProperty("user.home"), ".MirrorMe/weatherIcons");
        Path databaseFile = appDirectory.resolve(wT.getIcon()+".png");

        if (! Files.exists(databaseFile)) {
            try {
                // create the app directory if it doesn't already exist:
                Files.createDirectories(appDirectory);

                InputStream defaultDatabase = getClass().getClassLoader().getResourceAsStream("weatherIcons/" + wT.getIcon()+".png");
                Files.copy(defaultDatabase, databaseFile);
            } catch (IOException exc) {
                // handle exception here, e.g. if application can run without db,
                // set flag indicating it must run in non-db mode
                // otherwise this is probably a fatal exception, show message and exit...
                exc.printStackTrace();
            }
        }

        //File file = new File(databaseFile.toFile().toString());
        //System.out.println(file.toString() + "---------------------");
        //Image image = new Image(file.toString());

        try {
            weatherIconId.setImage(new Image(databaseFile.toUri().toString()));
            loc.setText(wT.getDescription().substring(0, 1).toUpperCase() + wT.getDescription().substring(1) + " " + wT.getCels());

        }catch(Exception e){
            loc.setText("Error News");
            e.printStackTrace();
        }

    }

    public void setTrainStatus(){

        TFLStatus tS = new TFLStatus();
        LocateMyCity lo = new LocateMyCity();

        int sizeOfService = tS.getTFL().size();
        int countGS = 0;

        String storeDelays = "";

        if(lo.getmyCityLocation().equalsIgnoreCase("London")) {

            try {

                for (Map.Entry<String, String> entry : tS.getTFL().entrySet()) {
                    //Line NOT equal to Good Service - Delay lines
                    if (!entry.getValue().equalsIgnoreCase("Good Service")) {
                        TFLline.setFont(new Font("Arial", 30));
                        TFLline.setStyle("-fx-font-weight: bold");
                        TFLline.setTextFill(Color.web("#ff270f"));
                        TFLline.setText("Services delays:");

                        storeDelays += entry.getKey() + ": " + entry.getValue() + "\n";

                        System.out.println("Name of Service: " + entry.getKey() + " " + entry.getValue() + "\n");
                        ++countGS;

                    }
                }

                if (countGS == 0) {
                    TFLline.setFont(new Font("Arial", 35));
                    TFLline.setStyle("-fx-font-weight: bold");
                    TFLline.setTextFill(Color.web("#25d039"));
                    TFLline.setText("Good Services: Underground & DLR");
                }
                System.out.println(countGS);
                //tS.getTFL().forEach((k,v)-> System.out.println(v));
            } catch (Exception e) {
                loc.setText("Error News");
            }

            trainUpdate.setFont(new Font("Arial", 27));
            trainUpdate.setStyle("-fx-font-weight: bold");
            trainUpdate.setTextFill(Color.web("#ffffff"));
            trainUpdate.setText(storeDelays.toString());

        }else{
            //Show city name!
            TFLline.setText(lo.getmyCityLocation());
        }
    }


    @FXML
    public void initialize() {

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(8000),
                ae -> getAndSetData()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
}
