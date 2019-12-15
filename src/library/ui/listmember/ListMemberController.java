package library.ui.listmember;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.db.DbHandler;

import java.lang.reflect.Member;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListMemberController  implements Initializable {

    ObservableList<Member> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Member> memberlist;

    @FXML
    private TableColumn<Member, String> namecol;

    @FXML
    private TableColumn<Member, String> idcol;

    @FXML
    private TableColumn<Member, String> emailcol;

    @FXML
    private TableColumn<Member, String> mobcol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobcol.setCellValueFactory(new PropertyValueFactory<>("no"));

        try {
            dataLoad();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dataLoad() throws SQLException {
        list.clear();
        ResultSet rs = DbHandler.excQuery("select * from member");
        while (rs.next())
        {
            list.add(new Member(rs.getString("name"),rs.getString("id"),rs.getString("email"),rs.getString("mob")));
        }
        memberlist.getItems().setAll(list);
    }

    public static class Member
    {
        String name,id,email,no;

        public Member(String name, String id, String email, String no) {
            this.name = name;
            this.id = id;
            this.email = email;
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getNo() {
            return no;
        }
    }
}
