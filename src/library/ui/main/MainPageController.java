package library.ui.main;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDrawerEvent;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import library.db.DbHandler;
import library.ui.alert.AlertMsg;
import library.ui.support.Check;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static library.ui.alert.AlertMsg.alertMsg;

public class MainPageController implements Initializable {


    ObservableList<Book> book = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> issuelist = FXCollections.observableArrayList();
    ObservableList<String> admindata = FXCollections.observableArrayList();


    String adminuser ;


    @FXML
    private JFXTextField searchtext;

    @FXML
    private JFXRadioButton contains;
    
    
    @FXML
    private JFXRadioButton title_rb;

    @FXML
    private ToggleGroup rbgrp;

    @FXML
    private JFXRadioButton publisher_rb;

    @FXML
    private JFXRadioButton id_rb;

    @FXML
    private JFXRadioButton author_rb;


    @FXML
    private TableView<Book> bookdatatable;

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

    //for tabpane 2

    @FXML
    private ListView<String> listdata;

    @FXML
    private JFXTextField ebookid;

    @FXML
    private JFXTextField memberid;

    @FXML
    private JFXTextField rbookid;

 

    @FXML
    private ListView<String> rbookdata;

    @FXML
    private JFXComboBox<String> searchcbox;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;

    VBox toolpane = null;

    @FXML
    private ListView<String> admindatalist;

    @FXML
    private VBox toolbar;

    public void setAdminuser(String adminuser) {
        this.adminuser = adminuser;
        AlertMsg.alertMsg("Admin user :  "+ adminuser + " ... Software is ready to work ..","info");
        System.out.println("one ");
        loadAdminData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        //setting up column in table

        titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorcol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishercol.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        availcol.setCellValueFactory(new PropertyValueFactory<>("avail"));

        DbHandler.createBookTable();
        DbHandler.createMemberTable();
        DbHandler.createIssueTable();

        searchcbox.getItems().addAll("Contains","Title Of Book","Book Id","Author","Publisher");
        searchcbox.setPromptText("Select Search criteria");
        searchcbox.setValue("");

        initDrawer();

        System.out.println("two");

    }

    public void loadAdminData()  {
        admindata.clear();
        ResultSet rs = DbHandler.excQuery("select * from login where id ='"+adminuser+"'");
        try
        {
            while (rs.next())
            {
                System.out.println("three");
          /*      admindata.add(" \t Name : "+ rs.getString("id"));
                admindata.add(" \t Mobile No  : "+ rs.getString("mob"));
                admindata.add(" \t Email ID : "+ rs.getString("email"));
                //admindatalist.getItems().addAll(admindata);
                admindatalist.setItems(admindata);
                */
                admindatalist.getItems().addAll("dsdsdssadsds");
            }
        }
        catch (Exception exc)
        {

        }
    }

    private void initDrawer() {


        try {
            toolpane = FXMLLoader.load(getClass().getClassLoader().getResource("library/ui/main/toolbox.fxml"));

            toolpane.getStylesheets().add("library/ui/support/common.css");
        } catch (IOException e) {
            e.printStackTrace();
        }



        drawer.setSidePane(toolpane);
        drawer.setPrefWidth(0);
        toolpane.setPrefWidth(drawer.getPrefWidth());
        toolpane.setPrefHeight(drawer.getPrefHeight());

        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
        transition.setRate(-1);

        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                    transition.setRate(transition.getRate()*-1);
                    transition.play();
                    if (drawer.isClosed())
                    {
                        drawer.open();
                        drawer.setPrefWidth(196);
                        toolpane.setPrefWidth(drawer.getPrefWidth());
                        toolpane.setPrefHeight(drawer.getPrefHeight());
                    }

                    else
                    {
                        drawer.close();
                        drawer.setPrefWidth(0);

                        toolpane.setPrefWidth(drawer.getPrefWidth());
                        toolpane.setPrefHeight(drawer.getPrefHeight());

                    }


            }
        });

    }

    private void load(String str) throws SQLException {

        book.clear();
        
        ResultSet rs = null;
        if(str == "contains")
        {
          rs = DbHandler.excQuery("select * from book where title like \"%"+searchtext.getText()+"%\" or id like \"%"+searchtext.getText()+"%\" or author like \"%"+searchtext.getText()+"%\" or publisher like \"%"+searchtext.getText()+"%\"");  
        }
        else
        {
           rs = DbHandler.excQuery("select * from book where "+str+" like  \"%"+searchtext.getText()+"%\"");
        }
         while(rs.next())
             {
                 String title = rs.getString("title");
                 String id = rs.getString("id");
                 String author =  rs.getString("author");
                 String publisher =  rs.getString("publisher");

                 Boolean avail = rs.getBoolean("avail");
                 book.add(new Book(title,id,author,publisher,avail));
             }
           //  bookdatatable.setItems(book);
        bookdatatable.getItems().clear();
        bookdatatable.getItems().setAll(book); 
    }

    public static class  Book
    {
         private final SimpleStringProperty title,id,author,publisher;
        private final SimpleBooleanProperty avail;


        public Book(String title,String id,String author,String publisher,Boolean avail)
        {
            this.title = new SimpleStringProperty(title);
            this.author =  new SimpleStringProperty(author);
            this.id =  new SimpleStringProperty(id);
            this.avail = new SimpleBooleanProperty(avail);
            this.publisher =  new SimpleStringProperty(publisher);



        }

        public String getTitle() {
            return title.get();
        }

        public String getId() {
            return id.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getPublisher() {
            return publisher.get();
        }



        public Boolean getAvail() {
            return  avail.get();
        }
    }


    public void search(ActionEvent event) {

        bookdatatable.getItems().clear();

        //"Contains","Title Of Book","Book Id","Author","Publisher"
        if (searchcbox.getValue().equals(""))
        {
            alertMsg("Please Select Search Criteria  ....","error");
            searchcbox.getStyleClass().add("wrong-credential");
        }
        else if (searchcbox.getValue().equals("Contains")) {
            try {
                load("contains");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        else if (searchcbox.getValue().equals("Title Of Book")) {
            try {
                load("title");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (searchcbox.getValue().equals("Book Id")) {
            try {
                load("id");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (searchcbox.getValue().equals("Author")) {
            try {
                load("author");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (searchcbox.getValue().equals("Publisher")) {
            try {
                load("publisher");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    //for tabpane 2

    @FXML
    void issue(ActionEvent event) throws SQLException {
        DbHandler.createIssueTable();

        if(memberid.getText().equals("") || ebookid.getText().equals(""))
           alertMsg("Please fill member id and Book id   ....","error");

        else if (!Check.checkId(ebookid.getText(),"book"))
          alertMsg("Invalid Book Id  ....","error");

        else if (!Check.checkId(memberid.getText(),"member"))
           alertMsg("Invalid Member Id  ....","error");

        else
        {
            boolean flag = true;
            ResultSet rs = DbHandler.excQuery("select avail from book where id = \""+ebookid.getText()+"\"");
            rs.next();
            if(rs.getBoolean("avail")==false)
               alertMsg("Book Already Issued ....","error");
            else {

                        rs = DbHandler.excQuery("select mid from issue");
                        while (rs.next())
                        {
                            if (rs.getString("mid").equals(memberid.getText()))
                            {
                                flag = false;
                                break;
                            }
                        }
                        rs = DbHandler.excQuery("select bid from issue");
                        while (rs.next())
                        {
                            if (rs.getString("bid").equals(ebookid.getText()))
                            {
                                flag = false;
                                break;
                            }
                        }
                        if (flag)
                        {
                                     DbHandler.excAction("insert into issue(bid,mid,issuedate,issueno) values(\""+ebookid.getText()+"\",\""+memberid.getText()+"\",curdate(),1)");
                                     DbHandler.excAction("update book set avail = false where id = \""+ebookid.getText()+"\"");
                                     loadData(event);
                                     alertMsg("Book Issued Sucessfully ....","info");
                        }
                        else
                          alertMsg("Another Book Already Issued to the reader ....","error");
                }
        }
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
    void close(ActionEvent event) {
            System.exit(0);
    }



    @FXML
    void loadData(ActionEvent event) throws SQLException {


        if(memberid.getText().equals("") || ebookid.getText().equals(""))
          alertMsg("Please fill member id and Book id   ....","error");

        else if (!Check.checkId(ebookid.getText(),"book"))
          alertMsg("Invalid Book Id  ....","error");

        else if (!Check.checkId(memberid.getText(),"member"))
           alertMsg("Invalid Member Id....","error");

        else
        {
            list.clear();
            ResultSet rs = DbHandler.excQuery("select * from book where id = \""+ebookid.getText()+"\"");
            list.add(" \n");
            list.add(" \t\t------------Book Information ----------\n");
            list.add(" \n");
            while(rs.next())
            {

                list.add(" \t\t title : "+ rs.getString("title"));
                list.add(" \t\t id : "+ rs.getString("id"));
                list.add(" \t\t author : "+ rs.getString("author"));
                list.add(" \t\t publisher : "+ rs.getString("publisher"));
                boolean flag = rs.getBoolean("avail");
                String str;
                if (flag)
                    str = "yes";
                else
                    str = "no";
                list.add(" \t\t availability : "+ str );

            }
            rs = DbHandler.excQuery("select * from member where id = \""+memberid.getText()+"\"");
            list.add(" \n");
            list.add(" \t\t------------Member Information ----------\n");
            list.add(" \n");
            while(rs.next())
            {
                list.add(" \t\t name of member : "+ rs.getString("name"));
                list.add(" \t\t id of member : "+ rs.getString("id"));
                list.add(" \t\t mobile no  : "+ rs.getString("mob"));
                list.add(" \t\t email id : "+ rs.getString("email"));
            }
            listdata.getItems().setAll(list);

        }
    }


    @FXML
    void loadIssueData(ActionEvent event) throws SQLException {

        issuelist.clear();
        if(rbookid.getText().equals(""))
            alertMsg("Please fill Book id   ....","error");
        else if (!Check.checkId(rbookid.getText(),"issue"))
            alertMsg("Book is not issued yet  ....","error");
        else
        {
//
//                    -----------+-------------+------+-----+---------+-------+
//                    | Field     | Type        | Null | Key | Default | Extra |
//                    +-----------+-------------+------+-----+---------+-------+
//                    | bid       | varchar(30) | YES  | MUL | NULL    |       |
//                    | mid       | varchar(20) | YES  | MUL | NULL    |       |
//                    | issuedate | date        | YES  |     | NULL    |       |
//                    | issueno   | int(2)      | YES  |     | NULL    |       |
//                    +-----------+-------------+------+-----+---------+-------+


            ResultSet rs = DbHandler.excQuery("select * from issue where bid = \""+rbookid.getText()+"\"");
            issuelist.add(" \n");
            issuelist.add(" \t\t-------Book issue information -----\n");
            issuelist.add(" \n");
            while(rs.next())
            {
//                String bid = rs.getString("bid");
//                String mid = rs.getString("mid");

                issuelist.add(" \t\t book id  : "+ rs.getString("bid"));
                issuelist.add(" \t\t member id : "+ rs.getString("mid"));
                issuelist.add(" \t\t issuedate : "+ rs.getString("issuedate"));
                issuelist.add(" \t\t no of issues : "+ rs.getString("issueno"));
                System.out.println( rs.getString("bid"));
            }

            rbookdata.getItems().setAll(issuelist);
            System.out.println("yes executing");
        }
    }

    @FXML
    void returnBook(ActionEvent event) throws SQLException {

        if (rbookid.getText().equals(""))
          alertMsg("Please fill  Book id   ....","error");

        else if (!Check.checkId(rbookid.getText(),"book"))
         alertMsg("Invalid Book Id  ....","error");

        else if (!Check.checkId(rbookid.getText(),"issue"))
            alertMsg("Book is not issued yet ....","error");

        else
        {
            ResultSet rs = DbHandler.excQuery("select count(*) from issue where bid = \""+rbookid.getText()+"\"");
            rs.next();
            if (rs.getInt(1)==0)
                alertMsg("This book with id "+rbookid.getText()+" is not issued ","error");
            else
            {
                DbHandler.excAction("delete from issue where bid = \""+rbookid.getText()+"\"");
                DbHandler.excAction("update book set avail = true where id= \""+rbookid.getText()+"\"");
                alertMsg("Book has been Successfuly returned","info");
            }
        }
        issuelist.clear();
        list.clear();
    }

    @FXML
    void renewBook(ActionEvent event) throws SQLException {
        if (rbookid.getText().equals(""))
             alertMsg("Please fill  Book id   ....","error");
        else if (!Check.checkId(rbookid.getText(),"book"))
            alertMsg("Invalid Book Id  ....","error");
        else if (!Check.checkId(rbookid.getText(),"issue"))
            alertMsg("Book is not issued yet ....","error");
        else
        {
            ResultSet rs = DbHandler.excQuery("select count(*) from issue where bid = \""+rbookid.getText()+"\"");
            rs.next();
            if (rs.getInt(1) == 1)
            {
                boolean  flag = false;
                rs = DbHandler.excQuery("select curdate(),issuedate from issue where bid =\""+rbookid.getText()+"\"");
                rs.next();
                Date curdate = rs.getDate(1);
                Date issuedate = rs.getDate(2);
                long monthdif =( curdate.getTime()-issuedate.getTime())/(1000*3600*24);
                System.out.println(monthdif);
                if (monthdif > 7 )
                    alertMsg(" you have collected book more than 7 days , you have to pay "+(monthdif-7)+" Rupiess  fine..\n  please submit charge at counter ..","info");
                rs = DbHandler.excQuery("select issueno from issue where bid = \""+rbookid.getText()+"\"");
                rs.next();
                int issueno ;
                if ((issueno = rs.getInt("issueno")) > 2)
                     alertMsg("Can't issue a single book more than 3 time... You have to return the book","error");
                else
                {
                    issueno++ ;
                    DbHandler.excAction("update issue set issueno = "+issueno+" where bid = \""+rbookid.getText()+"\"");
                    DbHandler.excAction("update issue set issuedate = curdate() where bid = \""+rbookid.getText()+"\"");
                    alertMsg("Book is renewed for 1 week ....","info");
                    loadIssueData(event);
                }
            }
            else
                 alertMsg("Book is not issued yet ....","error");
        }
        issuelist.clear();
        list.clear();
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
     @FXML
    void setting(ActionEvent event) {
        createWindow("library/ui/setting/setting.fxml","Settings");
    }

    @FXML
    void changeTabOpt(ActionEvent event) {
        book.clear();
        list.clear();
        issuelist.clear();

        bookdatatable.setItems(book);
        listdata.setItems(list);
        rbookdata.setItems(issuelist);
    }
}
