package eventmanagementsystem;

import java.sql.*;
public class Conn {
    Connection c;
    Statement s;
    Conn(){
        try{
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/eml", "root", "Rohit@123");
        s = c.createStatement();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
