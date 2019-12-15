package library.ui.alert;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import java.io.IOException;

public class AlertMsg {
    public  static  void alertMsg(String msg,String con)
    {
        try {

                if (con.equals("error"))
                    new Alertmsg2(msg,"AlertError");
                else if (con.equals("warning"))
                    new Alertmsg2(msg,"AlertWarning");
                else if (con.equals("confirm"))
                    new Alertmsg2(msg,"AlertConfirm");
                else if (con.equals("info"))
                    new Alertmsg2(msg,"AlertInfo");
                else
                    new Alertmsg2(msg,"AlertWarning");
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
