package sample;

import WeatherGs.Weather;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.WhileNode;
import sun.plugin.javascript.navig4.Anchor;

import java.io.File;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    static String i;

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



            ScrollBar scrollBarv = (ScrollBar)lblN.lookup(".scroll-bar:vertical");
            scrollBarv.setDisable(true);
            lblN.setWrapText(true);
            lblN.appendText("BBC NEWS" + "\n");
            lblN.setText(news);

            slowScrollToBottom(scrollBarv);
        }catch(Exception e){
            lblN.setText("Error News");
        }
    }

    static void slowScrollToBottom(ScrollBar scrollPane) {
        scrollPane.setValue(-0.2);
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(7),
                        new KeyValue(scrollPane.valueProperty(), 1)));
        animation.play();
    }

    public void setLocation(){
        WeatherToday wT = new WeatherToday();
        File file = new File("src\\weatherIcons\\"+ wT.getIcon() + ".png");
        Image image = new Image(file.toURI().toString());

        try {
            weatherIconId.setImage(image);
            loc.setText(wT.getDescription().substring(0, 1).toUpperCase() + wT.getDescription().substring(1) + " " + wT.getCels());

        }catch(Exception e){
            loc.setText("Error News");
        }
    }

    public void setTrainStatus(){

        TFLStatus tS = new TFLStatus();
        int sizeOfService = tS.getTFL().size();
        int countGS = 0;

        try {

            for (Map.Entry<String, String> entry : tS.getTFL().entrySet()) {
                //Line NOT equal to Good Service - Delay lines
                if(!entry.getValue().equalsIgnoreCase("Good Service")){
                    TFLline.setFont(new Font("Arial", 18));
                    TFLline.setStyle("-fx-font-weight: bold");
                    TFLline.setTextFill(Color.web("#ff270f"));
                    TFLline.setText("Services delays:");

                    trainUpdate.setText(entry.getKey() + ": " + entry.getValue());
                    System.out.println("Name of Service: " + entry.getKey() + " " + entry.getValue() + "\n");
                    ++countGS;

                }
            }

            if(countGS == 0 ){
                TFLline.setFont(new Font("Arial", 15));
                TFLline.setStyle("-fx-font-weight: bold");
                TFLline.setTextFill(Color.web("#25d039"));
                TFLline.setText("Good Services: Underground & DLR");
            }
            System.out.println(countGS);
            //tS.getTFL().forEach((k,v)-> System.out.println(v));
            } catch(Exception e){
            loc.setText("Error News");
        }
    }


    @FXML
    public void initialize() {

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(10000),
                ae -> getAndSetData()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



    //   getAndSetTheCurrentTime();
//       lblN.textProperty().bind(i);
    }
}
