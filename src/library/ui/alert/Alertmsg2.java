package library.ui.alert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Alertmsg2 {
    public Alertmsg2(String msg,String msgtype) throws IOException {
        String path = "library/ui/alert/"+ msgtype+".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
        loader.load();
        AlertInfoController controller = loader.getController();
        controller.setMsg(msg);
        Parent root = loader.getRoot();
        Stage s = new Stage();
        s.setResizable(false);
        s.setScene(new Scene(root));
        s.initModality(Modality.APPLICATION_MODAL);
        s.initStyle(StageStyle.UTILITY);
        s.showAndWait();
    }
}
