package library.ui.login.precovery;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import library.db.DbHandler;
import library.ui.support.Check;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static library.ui.alert.AlertMsg.alertMsg;

public class PrecoveryController implements Initializable {

    @FXML
    private JFXTextField data;

    @FXML
    private JFXRadioButton email_rb;

    @FXML
    private ToggleGroup rbgrp2;

    @FXML
    private JFXRadioButton mob_rb;

    @FXML
    private JFXRadioButton sdate_rb;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton change_btn;

    @FXML
    private JFXButton password_btn;

    @FXML
    void change(ActionEvent event) throws SQLException {

        if (data.getText().equals("")  )
            alertMsg("Please select recovery option....","error");
        else if (!(email_rb.isSelected() || mob_rb.isSelected() || sdate_rb.isSelected()))
            alertMsg("please select Change Criteria","error");
        else  if (email_rb.isSelected())
        {
            boolean flag = true;
            ResultSet rs = DbHandler.excQuery("select count(*) from login where id = \""+id.getText()+"\" and email =\""+data.getText()+"\"");
            while (rs.next())
            {
                if (rs.getInt(1)==1)
                {
                    password.setDisable(false);
                    password_btn.setDisable(false);
                    flag = false;
                }
            }
            if (flag)
                alertMsg("Wrong Data Entered ","error");
        }
        else if (mob_rb.isSelected())
        {
            boolean flag = true;
            ResultSet rs = DbHandler.excQuery("select count(*) from login where id = \""+id.getText()+"\" and mob =\""+data.getText()+"\"");
            while (rs.next())
            {
                if (rs.getInt(1)==1)
                {
                    password.setDisable(false);
                    password_btn.setDisable(false);
                    flag = false;
                }
            }
            if (flag)
            {
                alertMsg("Wrong Data Entered ","error");
            }
        }
        else if (sdate_rb.isSelected())
        {
            boolean flag = true;
            ResultSet rs = DbHandler.excQuery("select count(*) from login where id = \""+id.getText()+"\" and signupdate =\""+data.getText()+"\"");
            while (rs.next())
            {
                if (rs.getInt(1)==1)
                {
                    password.setDisable(false);
                    password_btn.setDisable(false);
                    flag = false;
                }
            }
            if (flag)
            {
                alertMsg("Wrong Data Entered ","error");
            }
        }

    }

    @FXML
    void verify(ActionEvent event) {
        if (change_btn.isDisable())
            {
                if (Check.checkId(id.getText(),"login"))
                {
                    data.setDisable(false);
                    email_rb.setDisable(false);
                    mob_rb.setDisable(false);
                    sdate_rb.setDisable(false);
                    change_btn.setDisable(false);
                }
                else
                    alertMsg("User account not exists ","error");
            }
            else
                alertMsg("User Id already verified ....","error");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.setDisable(true);
        email_rb.setDisable(true);
        mob_rb.setDisable(true);
        sdate_rb.setDisable(true);
        change_btn.setDisable(true);
        password.setDisable(true);
        password_btn.setDisable(true);
    }

    @FXML
    void savePassword(ActionEvent event) {
            if (password.getText().length() < 8)
                alertMsg("password should be minimum 8 digit ....","error");
            else
            {
                DbHandler.excAction("update login set password = \""+password.getText()+"\" where id = \""+id.getText()+"\"");
                alertMsg("password Updated Successfully ....","info");
            }
    }
}
