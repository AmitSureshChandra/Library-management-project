package library.ui.alert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AlertLaunch extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AlertInfo.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();

    }
}
