package library.ui.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setResizable(true);
        primaryStage.setTitle("Login");
        primaryStage.getIcons().addAll(new Image("/icons/reading.png"));
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
