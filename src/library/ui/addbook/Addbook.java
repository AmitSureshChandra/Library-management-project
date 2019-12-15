package library.ui.addbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Addbook extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("addbook.fxml"))));
            primaryStage.show();
            //primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setTitle("Add Book");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
