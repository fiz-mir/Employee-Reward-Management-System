package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import util.connectDB;
import Bean.LoginBean;


public class LoginDAO {

    public LoginDAO() {
    }
    
    //call db connection (static method) to connect to java db
    public Connection con = connectDB.createConnection();
    
    //To get current local date
    LocalDate currentDate = LocalDate.now();
    Date currentSqlDate = Date.valueOf(currentDate);
    
    public String authenticateUser(LoginBean loginBean){
    int adminID = loginBean.getAdminID(); //keeping user entered value
    String password = loginBean.getPassword();//keeping user entered value
    
    Connection con = null;
    Statement stmt = null;
    ResultSet resultSet = null;
    
    try {
        con = connectDB.createConnection();//establishing connection
        stmt = con.createStatement(); //stmt used to write queries
        String sql; //sql select stmt
        sql = "SELECT administrator.administratorstatus_id, administrator.administrator_id, employee.employee_password "
        + "FROM employee "
        + "LEFT JOIN administrator ON employee.employee_id = administrator.employee_id "
        + "WHERE administrator.administrator_id = ? AND employee.employee_password = ?";

        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, adminID);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) { //record found
            int admstatsid = resultSet.getInt("administratorstatus_id");
            if(admstatsid == 50){                     
            return "SUCCESS"; //match with value in db
            }
            else{
                return "The administrator ID is no longer valid";
            }
        }
    }
    catch (SQLException e){
        e.printStackTrace();
    }
        return "Invalid user credentials";
                      
    }
    public void AddLoginSession(int adminID){
        
          //Get data from bean
        
          LocalDateTime dateTime = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
          String timestamp = dateTime.format(formatter);

          Random random = new Random();
          int maxDigits = 10;
          int maxNumber = (int) Math.pow(10, maxDigits) - 1;
          int randomInt = random.nextInt(maxNumber) + 1;
          String randomString = Integer.toString(randomInt);
           
          Connection con = null;
          Statement stmt = null;
          ResultSet resultSet = null;

        try {
            con = connectDB.createConnection();//establishing connection
            stmt = con.createStatement(); //stmt used to write queries
            String sql; //sql select stmt
            sql = "insert into AUDIT_LOGIN (auditlogin_id, auditlogin_in, administrator_id) values(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, randomString);
            ps.setString(2, timestamp);
            ps.setInt(3, adminID);
            
            // Execute the query to insert data into the database
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
                      
    }  
    public static final String NAME_QUERY = "SELECT EMPLOYEE.EMPLOYEE_NAME FROM ADMINISTRATOR " +
            "JOIN EMPLOYEE ON ADMINISTRATOR.EMPLOYEE_ID = EMPLOYEE.EMPLOYEE_ID " +
            "WHERE ADMINISTRATOR.ADMINISTRATOR_ID = ?";

    public static final String STATUS_QUERY = "SELECT ADMINISTRATOR_STATUS.ADMINISTRATORSTATUS_NAME, ADMINISTRATOR.ADMINISTRATORSTATUS_ID FROM ADMINISTRATOR " +
            "JOIN ADMINISTRATOR_STATUS ON ADMINISTRATOR.ADMINISTRATORSTATUS_ID = ADMINISTRATOR_STATUS.ADMINISTRATORSTATUS_ID " +
            "WHERE ADMINISTRATOR.ADMINISTRATOR_ID = ?";
}

