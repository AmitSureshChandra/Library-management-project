/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amit
 */
public class DbHandler {
      public static Connection con = null; 
      public static Connection getConnection()
      {
        if(con==null)
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection ("jdbc:mysql://localhost:3306", "root", "");
                excAction("create database if not exists libman");
                excAction("use libman");
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return con;
      }

      public static boolean excAction(String sql)
      {
          try {
              Statement st = getConnection().createStatement();
              boolean flag = st.execute(sql);
              return flag;
          } catch (SQLException ex) {
              Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
              return false;
          }
      }
       public static ResultSet excQuery(String sql)
      {
          try {
              Statement st = getConnection().createStatement();
              ResultSet rs = st.executeQuery(sql);
              return rs;
          } catch (SQLException ex) {
              Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
      }
      public static void createBookTable()
      {
          ResultSet rs = excQuery(" select count(*) from information_schema.tables where information_schema.tables.table_name = \"book\" and table_schema = \"libman\";");

          try {
              rs.next();
              if ((rs.getInt(1)) == 0)
              {
                  excAction("create table book(title varchar(30) unique key not null,id varchar(20) primary key,author varchar(50),publisher varchar(50),avail boolean default true)");
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }

    public static void createMemberTable()
    {
        ResultSet rs = excQuery(" select count(*) from information_schema.tables where information_schema.tables.table_name = \"member\" and table_schema = \"libman\";");

        try {
            rs.next();
            if ((rs.getInt(1)) == 0)
            {
                excAction("create table member(name varchar(30) unique key not null,id varchar(20) primary key,mob varchar(15) unique key,email varchar(50) unique key)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createIssueTable()
    {
        ResultSet rs = excQuery(" select count(*) from information_schema.tables where information_schema.tables.table_name = \"issue\" and table_schema = \"libman\";");

        try {
            rs.next();
            if ((rs.getInt(1)) == 0)
            {
                excAction("create table issue(bid varchar(30),mid varchar(20) ,issuedate date,issueno int(2),foreign key (bid) references book(id), foreign key (mid)  references member(id))");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createLoginTable()
    {
        ResultSet rs = excQuery(" select count(*) from information_schema.tables where information_schema.tables.table_name = \"login\" and table_schema = \"libman\";");

        try {
            rs.next();
            if ((rs.getInt(1)) == 0)
            {
                excAction("create table login(id varchar(20) primary key,password varchar(30) not null,mob varchar(15) unique key,email varchar(50) unique key,signupdate date)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
