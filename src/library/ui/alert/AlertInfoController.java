package library.ui.alert;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertInfoController implements Initializable {
    @FXML
    private Label msg;

    @FXML
    private JFXButton ok_button;

    @FXML
    private FontAwesomeIconView ok;

    @FXML
    private FontAwesomeIconView error;
    @FXML
    private FontAwesomeIconView info;

    @FXML
    private AnchorPane alert_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setMsg(String str)
    {
        msg.setText(str);
    }
    @FXML
    void dispose(ActionEvent event) {
         ((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
    }
}
