package library.ui.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import library.db.DbHandler;

import java.io.IOException;
import java.sql.SQLException;

public class MainPage extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("library/ui/main/MainPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setResizable(true);
        primaryStage.setTitle("Library Management Software");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().addAll(new Image("/icons/reading.png"));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }


}


