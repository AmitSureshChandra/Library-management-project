package library.ui.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ToolBoxController implements Initializable {



    @FXML
    private JFXButton listbook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //  listbook.getStyleClass().add("toolbutton");
    }

    public  void createWindow(String path,String title)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage s  = new Stage();
        s.setScene(new Scene(root));
        s.getIcons().addAll(new Image("/icons/reading.png"));
        s.setTitle(title);
        s.initModality(Modality.APPLICATION_MODAL);
        s.show();
        s.setResizable(false);
    }


    @FXML
    void removeBook(ActionEvent event) {
        createWindow("library/ui/removebook/removeBook.fxml","Remove Book");
    }
    @FXML
    void removeMember(ActionEvent event) {
        createWindow("library/ui/removemember/RemoveMember.fxml","Remove Member");
    }


    @FXML
    void listBook(ActionEvent event) {
        createWindow("library/ui/listbook/ListBook.fxml"," Book List");

    }

    @FXML
    void listMember(ActionEvent event) {
        createWindow("library/ui/listmember/ListMember.fxml"," Member list");
    }

    @FXML
    void listIssue(ActionEvent event) {
        createWindow("library/ui/listissue/ListIssue.fxml"," Issued list");
    }
    @FXML
    void logout(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("library/ui/login/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage s  = new Stage();
        s.setScene(new Scene(root));
        s.setTitle("Login");
        s.show();
        s.setResizable(false);
        s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.close();
    }
    @FXML
    void addBook(ActionEvent event) {
        createWindow("library/ui/addbook/addbook.fxml","Add Book");
    }
    @FXML
    void addMember(ActionEvent event) {
        createWindow("library/ui/addmember/addmember.fxml","Add Member");
    }


}
