package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectDB{
    public static Connection createConnection(){
        Connection conn = null;
        String driver = "org.apache.derby.jdbc.ClientDriver";//Java db driver
        String url = "jdbc:derby://localhost:1527/ORION_ERMSDB";//Java db
        String dbUser = "root", dbPassword = "root";
    
        try {
            try {
                Class.forName(driver);
            } 
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            //connect to db
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
            System.out.println("Printing connection object " + conn);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
