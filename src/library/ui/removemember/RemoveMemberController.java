package library.ui.removemember;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.db.DbHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import static library.ui.alert.AlertMsg.alertMsg;

public class RemoveMemberController {
    @FXML
    private TextField id;

    @FXML
    void remove(ActionEvent event) throws SQLException {

        ResultSet rs = DbHandler.excQuery("select count(*) from issue where mid = \""+id.getText()+"\"");
        rs.next();
        if (rs.getInt(1)>0)
            alertMsg("Member is not returned book .....","error");

        else
        {
            rs = DbHandler.excQuery("select count(*) from member where id = \""+id.getText()+"\"");
            rs.next();
            if (rs.getInt(1)==1)
            {
                DbHandler.excAction("delete from member where id = \""+id.getText()+"\"");
                alertMsg("member is removed from database ","info");
            }
            else
              alertMsg("member is not exist in database ....","error");

        }

    }

}
