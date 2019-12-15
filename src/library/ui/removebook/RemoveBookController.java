package library.ui.removebook;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.db.DbHandler;
import library.ui.support.Validator;

import java.sql.ResultSet;
import java.sql.SQLException;

import static library.ui.alert.AlertMsg.alertMsg;

public class RemoveBookController {

    @FXML
    private JFXTextField id;



    @FXML
    void remove(ActionEvent event) throws SQLException {


        if (id.getText().equals(""))
             alertMsg("Please fill  Book id   ....","error");

        else
        {
            ResultSet rs = DbHandler.excQuery("select count(*) from issue where bid = \""+id.getText()+"\"");
            rs.next();
            if (rs.getInt(1)>0)
              alertMsg("Book is not returned ..pls first submit then remove  ....","error");

            else
            {
                rs = DbHandler.excQuery("select count(*) from book where id = \""+id.getText()+"\"");
                rs.next();
                if (rs.getInt(1)==1)
                {
                    DbHandler.excAction("delete from book where id = \""+id.getText()+"\"");
                    alertMsg("Book is removed from database ","info");
                }
                else
                  alertMsg("Book is not exist in database ....","error");

            }
        }
    }
}
