package library.ui.listbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.db.DbHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListBookController  implements Initializable {

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Book> listbook;

    @FXML
    private TableColumn<Book, String> titlecol;

    @FXML
    private TableColumn<Book, String> idcol;

    @FXML
    private TableColumn<Book, String> authorcol;

    @FXML
    private TableColumn<Book, String> publishercol;

    @FXML
    private TableColumn<Book, Boolean> availcol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorcol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishercol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        availcol.setCellValueFactory(new PropertyValueFactory<>("avail"));

        try {
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void load() throws SQLException {
        list.clear();
        ResultSet rs = DbHandler.excQuery("select * from book");
        while (rs.next())
        {
            String title = rs.getString("title");
            String id = rs.getString("id");
            String author = rs.getString("author");
            String publisher = rs.getString("publisher");
            Boolean avail = rs.getBoolean("avail");
            list.add(new Book(title,id,author,publisher,avail));
        }

        listbook.getItems().setAll(list);
    }

    public  static class Book
    {
        String title,id,author,publisher;
        Boolean avail;

        public Book(String title, String id, String author, String publisher, Boolean avail) {
            this.title = title;
            this.id = id;
            this.author = author;
            this.publisher = publisher;
            this.avail = avail;
        }

        public String getTitle() {
            return title;
        }

        public String getId() {
            return id;
        }

        public String getAuthor() {
            return author;
        }

        public String getPublisher() {
            return publisher;
        }

        public Boolean getAvail() {
            return avail;
        }
    }
}
