package library.ui.addmember;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Addmember extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("addmember.fxml"))));
            primaryStage.show();
          //  primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setTitle("Add Member");
            primaryStage.setResizable(false);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
