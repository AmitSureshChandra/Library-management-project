package library.ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import library.db.DbHandler;
import library.ui.main.MainPageController;
import library.ui.support.Check;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static library.ui.alert.AlertMsg.alertMsg;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;


    @FXML
    void login(ActionEvent event) throws SQLException {


        if (username.getText().equals("") || password.getText().equals(""))
        {
            alertMsg("Please fill username and password  ....","error");
            username.getStyleClass().add("wrong-credential");
            password.getStyleClass().add("wrong-credential");
        }
        else
        {
            ResultSet rs = DbHandler.excQuery("select count(*) from login where id =  \""+ username.getText()+"\"");
            rs.next();
           if(rs.getInt(1)!=1)
           {
               alertMsg("Account doesn't exist....","error");
               username.getStyleClass().add("wrong-credential");
           }
           else
           {
                rs = DbHandler.excQuery("select count(*) from login where id = \""+username.getText()+"\" and password =\""+password.getText()+"\"");
                rs.next();
               try {
                   if (rs.getInt(1)==1)
                   {
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/ui/main/MainPage.fxml"));
                       Parent root = loader.load();
                       MainPageController con = new MainPageController();
                       con.setAdminuser(username.getText());



                       Stage s  = new Stage();
                       s.getIcons().addAll(new Image("/icons/reading.png"));
                       s.setScene(new Scene(root));
                       s.setTitle("Library Management System");

                       s.show();

                       s = (Stage) ((Node)event.getSource()).getScene().getWindow();
                       s.close();
                   }
                   else
                   {
                       alertMsg("Wrong password....","error");
                       password.getStyleClass().add("wrong-credential");
                   }
               } catch (SQLException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }

    }

    @FXML
    void signup(ActionEvent event) {
        new LoadWindow().createWindow("library/ui/login/Signup.fxml","Sign Up",false,false);
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    void forget(ActionEvent event) {
        new LoadWindow().createWindow("library/ui/login/precovery/precovery.fxml","Password Recovery",false,true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DbHandler.createLoginTable();
    }
}
