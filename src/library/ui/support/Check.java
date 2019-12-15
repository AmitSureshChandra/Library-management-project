package library.ui.support;

import javafx.scene.control.Alert;
import library.db.DbHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Check {
    public static boolean checkId(String id ,String table)
    {
        ResultSet rs = null;
        if (table == "issue")
        {
            rs = DbHandler.excQuery("select count(*) from "+ table+" where bid = \""+id+"\"");
        }
        else
        {
            rs = DbHandler.excQuery("select count(*) from "+ table+" where id = \""+id+"\"");
        }
        try {
            rs.next();
            if(rs.getInt(1)!= 1)
            {
                return false;
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }

}
