package library.ui.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

public class LoadWindow {

    public  void createWindow(String path, String title,boolean resizable ,boolean parent )
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        Stage s  = new Stage();
        s.getIcons().addAll(new Image("/icons/reading.png"));
        s.setScene(new Scene(root));
        s.setTitle(title);
        if (parent)
            s.initModality(Modality.APPLICATION_MODAL);
        s.show();
        
       // s.initModality(Modality.APPLICATION_MODAL);
        s.setResizable(resizable);
    }
}
