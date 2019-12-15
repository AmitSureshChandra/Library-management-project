package library.ui.listissue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.db.DbHandler;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListIssueController implements Initializable {

    ObservableList<Issue> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Issue> listissuedata;

    @FXML
    private TableColumn<Issue, String> bookid;

    @FXML
    private TableColumn<Issue, String> memberid;

    @FXML
    private TableColumn<Issue, Integer> count;

    @FXML
    private TableColumn<Issue, Date> date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DbHandler.createIssueTable();
        bookid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        memberid.setCellValueFactory(new PropertyValueFactory<>("mid"));
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws SQLException {
        list.clear();
        ResultSet rs = DbHandler.excQuery("select * from issue");
        while (rs.next())
        {
            list.add(new Issue(rs.getString("bid"),rs.getString("mid"),rs.getInt("issueno"),rs.getDate("issuedate")));
        }
        listissuedata.getItems().setAll(list);
    }

    public static class Issue
    {
        public  String bid,mid;
        public Integer count ;
        Date date;

        public Issue(String bid, String mid, Integer count, Date date) {
            this.bid = bid;
            this.mid = mid;
            this.count = count;
            this.date = date;
        }

        public String getBid() {
            return bid;
        }

        public String getMid() {
            return mid;
        }

        public Integer getCount() {
            return count;
        }

        public Date getDate() {
            return date;
        }
    }
}
