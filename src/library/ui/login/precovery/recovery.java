package library.ui.login.precovery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class recovery extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("precovery.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setResizable(true);
        primaryStage.setTitle("Recovery");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
