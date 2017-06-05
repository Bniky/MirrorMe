package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application implements Runnable{

    static String i;

    @FXML
    private Label lblN;

    @Override
    public void start(Stage primaryStage) throws Exception{
        WeatherToday weather = new WeatherToday();


        String css = this.getClass().getResource("myStyle.css").toExternalForm();

        String w = weather.getDescription();
        String city = weather.getCityN();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        Scene scene = new Scene(root, 575, 400);

        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setFullScreen(true);

        scene.getStylesheets().add(css);
        primaryStage.show();


        /*StackPane pane = new StackPane();


        Label wLabel = new Label(city + " " + w);
        wLabel.setWrapText(true);
        wLabel.setTranslateY(100);
        pane.getChildren().add(wLabel);

        Scene scene = new Scene(pane, 375, 200);
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        LocalTime watch = LocalTime.now();
        DateTimeFormatter shortTime = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(watch.format(shortTime));
        launch(args);

    }

    public void run() {


    }
}
