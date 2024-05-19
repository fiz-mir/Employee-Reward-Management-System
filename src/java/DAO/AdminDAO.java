package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import Bean.AdminBean;
import util.connectDB;



public class AdminDAO {

    public AdminDAO() {
    }
    
    //call db connection (static method) to connect to java db
    public Connection con = connectDB.createConnection();
    
    //To get current local date
    LocalDate currentDate = LocalDate.now();
    Date currentSqlDate = Date.valueOf(currentDate);
       
     public void addAdmin(AdminBean adBean){
        int employeeid = adBean.getEmployee_id();
        int adminstats_id = adBean.getAdministratorstatus_id();
        int adminrole_id = adBean.getAdministratorrole_id();
        
        String sql = "insert into administrator (administrator_date, administratorstatus_id, administratorrole_id, employee_id)"
                + " values (?,?,?,?)";
        PreparedStatement ps;
        try {
            //create preparestatement
            ps = con.prepareStatement(sql);
            ps.setDate(1, currentSqlDate);
            ps.setInt(2, adminstats_id);
            ps.setInt(3, adminrole_id);
            ps.setInt(4, employeeid);
            ps.executeUpdate();
            con.close();            
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static final String ADMIN_QUERY = "SELECT a.ADMINISTRATOR_ID, e.EMPLOYEE_NAME, ast.ADMINISTRATORSTATUS_NAME " +
            "FROM ADMINISTRATOR a LEFT JOIN EMPLOYEE e USING (EMPLOYEE_ID) " +
            "LEFT JOIN ADMINISTRATOR_STATUS ast USING (ADMINISTRATORSTATUS_ID)";
     
     
    public AdminBean getAdmin(int employeeId){
        try {
            String sql = null;
            
            sql = "SELECT administrator_id, administratorstatus_id, administratorrole_id, employee_id FROM ADMINISTRATOR LEFT JOIN EMPLOYEE USING (employee_id)"
                     + "WHERE employee_id=" + employeeId;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            int administratorstatus_id = rs.getInt("administratorstatus_id");
            int administratorrole_id = rs.getInt("administratorrole_id");
            int employee_id = rs.getInt("employee_id");
            int admin_id = rs.getInt("administrator_id");
            
            AdminBean empBean = new  AdminBean(administratorstatus_id,administratorrole_id,employee_id,admin_id);
            con.close();
            return empBean;
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
       public String getAdminRole(int adminId){
           
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet = null;
            
        try {
            con = connectDB.createConnection();//establishing connection
            stmt = con.createStatement(); //stmt used to write queries
            String sql; //sql select stmt
            
            sql = "SELECT administratorrole_id FROM ADMINISTRATOR"
                + " WHERE administrator_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, adminId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //record found
                int admroleid = resultSet.getInt("administratorrole_id");
                if(admroleid == 60){                     
                return "MANAGER"; //match with value in db
                }
                else{
                    return "MODERATOR";
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
          
}

