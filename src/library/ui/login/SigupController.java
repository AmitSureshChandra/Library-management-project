package library.ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import library.db.DbHandler;

import java.math.BigInteger;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static library.ui.alert.AlertMsg.alertMsg;

public class SigupController implements Initializable {

    @FXML
    private JFXTextField username;


    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField  email;

    @FXML
    private JFXTextField  mob;

    @FXML
    void clear(ActionEvent event) {
        username.setText("");
        password.setText("");
        email.setText("");
        mob.setText("");
    }

    @FXML
    void submit(ActionEvent event) throws SQLException {
        if(username.getText().equals("") || password.getText().equals("") || email.getText().equals("") || mob.getText().equals("") )
        {
            alertMsg("Please fill all details....","error");
            username.getStyleClass().add("wrong-credential");
            password.getStyleClass().add("wrong-credential");
            email.getStyleClass().add("wrong-credential");
            mob.getStyleClass().add("wrong-credential");

        }
        else
        {

            String emailtext = this.email.getText();
            boolean flag1 = false,flag2 = false,flag3 = false;
            int posat =0,posd = 0;
            for (int i = 0; i < emailtext.length(); i++) {
                if (emailtext.charAt(i) == '@')
                {
                    flag1 = true;
                    posat = i;
                    break;
                }

            }
            for (int i = posat+1; i < emailtext.length(); i++) {
                if (emailtext.charAt(i) == '.')
                {
                    flag2 = true;
                    posd = i;
                    break;
                }
            }
            if (  emailtext.length() > posd + 2  && posat < posd -1 &&  posat >1 )
                flag3 =true;


            if (flag1 && flag2 && flag3)
            {
                if (mob.getText().length() >= 13)
                {
                    alertMsg(" Mobile no should be max 12 digit ","error");
                    mob.getStyleClass().add("wrong-credential");
                }
                else if (mob.getText().length() < 10)
                {
                    alertMsg(" Mobile no should be minimum 10 digit ","error");
                    mob.getStyleClass().add("wrong-credential");
                }
                else
                {
                    char [] digit = {'0','1','2','3','4','5','6','7','8','9'};
                    boolean flag4 = false;
                    String mobno = mob.getText();
                    for (int i = 0; i < mobno.length() ; i++)
                    {
                        flag4 = false;
                        for (char c : digit )
                        {
                            if (c == mobno.charAt(i))
                            {
                                flag4 = true;
                                break;
                            }
                        }
                    }


                    if (!flag4 )
                    {
                        alertMsg("Invalid Mobile no","error");
                        mob.getStyleClass().add("wrong-credential");
                    }
                    else
                    {
                        if (password.getText().length() < 8)
                        {
                            alertMsg("minimum 8 chars in password required","error");
                            password.getStyleClass().add("wrong-credential");
                        }
                        else
                        {
                            ResultSet rs = DbHandler.excQuery("select count(*) from login where id = \""+username.getText()+"\"");
                            rs.next();
                            if (rs.getInt(1)!=1)
                            {
                                DbHandler.excAction("insert into login(id,password,mob,email,signupdate) values (\""+username.getText()+"\",\""+password.getText()+"\",\""+mob.getText()+"\",\""+email.getText()+"\",curdate())");
                                alertMsg("Account added ....","info");
                                new LoadWindow().createWindow("library/ui/login/Login.fxml","Login",false,false);

                                Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
                                s.close();
                            }
                            else
                            {
                                alertMsg("Already have an account ....","error");
                                username.getStyleClass().add("wrong-credential");
                            }
                        }
                    }
                }

            }
            else
            {
                alertMsg("Wrong Email id format ....( eg. abd@msg.com )","error");
                email.getStyleClass().add("wrong-credential");
            }
        }
    }

    @FXML
    void login(ActionEvent event) {


        new LoadWindow().createWindow("library/ui/login/Login.fxml","Login",false,false);
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DbHandler.createLoginTable();
    }
}
