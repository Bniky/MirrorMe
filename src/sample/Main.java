package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.scene.Node;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        WeatherToday weather = new WeatherToday();

        String w = weather.getDescription();
        String city = weather.getCityN();

        Parent root = FXMLLoader.load(getClass().getResource("C:\\Users\\Nicholas\\IdeaProjects\\MirrorMe\\src\\sample\\sample.fxml"));
        primaryStage.setScene(new Scene(root, 500,375));
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
}
