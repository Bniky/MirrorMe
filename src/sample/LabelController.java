package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.WhileNode;
import sun.plugin.javascript.navig4.Anchor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicholas on 01/06/2017.
 */
public class LabelController {

    @FXML
    private Label lblN;

    @FXML
    private Label lblTime;




    static String i;

    public void getAndSetData(){
        setTime();
        setNews();
    }

    public void setTime(){
        try {
            LocalTime watch = LocalTime.now();
            DateTimeFormatter shortTime = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
            i = shortTime.format(watch);
            lblTime.setText(i);

        }catch(Exception e){
            lblTime.setText("Error T");
        }
    }

    public void setNews(){

        try {
            String news = "";

            for(String n: new News().getHeadLine()){
                news += n + "\n";
            }

            lblN.setText(news);

        }catch(Exception e){
            lblN.setText("Error News");
        }
    }

    @FXML
    public void initialize() {

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> getAndSetData()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    //   getAndSetTheCurrentTime();
//       lblN.textProperty().bind(i);
    }
}
