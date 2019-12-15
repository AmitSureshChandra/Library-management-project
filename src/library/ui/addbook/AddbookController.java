package library.ui.addbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import library.db.DbHandler;
import library.ui.support.Check;
import library.ui.support.Validator;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static library.ui.alert.AlertMsg.alertMsg;

public class AddbookController implements Initializable {
    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField author;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Validator.fieldValidator(id);
//        Validator.fieldValidator(title);
//        Validator.fieldValidator(publisher);
//        Validator.fieldValidator(author);
    }

    @FXML
    private JFXTextField publisher;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton clear;

    @FXML
    void clear(ActionEvent event) {
        title.setText("");
        id.setText("");
        publisher.setText("");
        author.setText("");
    }

    @FXML
    void save(ActionEvent event) {

        if (id.getText().isEmpty()|| title.getText().isEmpty()|| author.getText().isEmpty()|| publisher.getText().isEmpty())
            alertMsg("Please fill all details","error");
        else
        {
            if(Check.checkId(id.getText(),"book"))
                alertMsg("Book with id already exists","error");
            else
            {
                String sql = "insert into book(id,title,author,publisher,avail) values(\""+id.getText()+"\",\""+title.getText()+"\",\""+author.getText()+"\",\""+publisher.getText()+"\",true);";
                DbHandler.excAction(sql);
                alertMsg("Book Added","confirm");
                clear(event);
            }
        }
    }
}
