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
import javafx.scene.control.TextArea;
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

    @Override
    public void start(Stage primaryStage) throws Exception{

        String css = this.getClass().getResource("myStyle.css").toExternalForm();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        Scene scene = new Scene(root, 650, 400);

        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setResizable(false);
        primaryStage.setMaximized(true);

        scene.getStylesheets().add(css);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }

    public void run() {


    }
}
