package library.ui.addmember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import library.db.DbHandler;
import library.ui.support.Check;

import java.net.URL;
import java.util.ResourceBundle;

import static library.ui.alert.AlertMsg.alertMsg;

public class AddmemberController implements Initializable {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField mobile;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXButton addmember;

    @FXML
    private JFXButton clear;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    boolean emailValidation( String emailtext)
    {

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


        return (flag1 && flag2 && flag3);
    }

    boolean mobValidation(String mobno)
    {
        if (mobno.length() >= 13)
        {
            alertMsg(" Mobile no should be max 12 digit ","error");
            return  false;
        }
        else if (mobno.length() < 10)
        {
            alertMsg(" Mobile no should be minimum 10 digit ","error");
            return  false;
        }

        else {
            char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            boolean flag4 = false;

            for (int i = 0; i < mobno.length(); i++) {
                flag4 = false;
                for (char c : digit) {
                    if (c == mobno.charAt(i)) {
                        flag4 = true;
                        break;
                    }
                }
            }


            if (!flag4) {
                alertMsg("Invalid Mobile no","error");
                return false;
            }
        }
            return true;
    }

    @FXML
    void addMember(ActionEvent event) {
        String mid = id.getText();
        String mname = name.getText();
        String memail = email.getText();
        String mmobile = mobile.getText();
        Boolean flag = mid.isEmpty() ||mname.isEmpty() ||memail.isEmpty() ||mmobile.isEmpty();
        if(flag)
            alertMsg("Please fill all details","error");
        else {
            if (Check.checkId(mid, "member"))
               alertMsg("member already exist","error");
            else
                {
                            if(emailValidation(email.getText()))
                            {
                                if (mobValidation(mobile.getText()))
                                {
                                    String sql = "insert into member(id,name,mob,email) values(\"" + mid + "\",\"" + mname + "\",\"" + mmobile + "\",\"" + memail + "\")";
                                    DbHandler.excAction(sql);
                                    alertMsg("member Added","confirm");
                                    clear(event);
                                }
                            }
                            else
                                alertMsg("Book Added","Wrong Email id format ....( eg. abd@msg.com )");

                }
        }
    }

    @FXML
    void clear(ActionEvent event) {

        id.setText("");
        name.setText("");
        mobile.setText("");
        email.setText("");

    }
}
