package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.connectDB;
import Bean.EmployeeBean;
import java.sql.Statement;

public class EmployeeDAO {
    
    public EmployeeDAO(){
        
    }
    //call db connection (static method) to connect to java db
    public Connection con = connectDB.createConnection();
    
    public static final String EMPLOYEE_QUERY = "SELECT e.employee_id, e.employee_name, e.employee_email, er.employeerole_name, es.employeestatus_name, " +
         "el.employeelevel_name, et.employeetype_name, ed.employeedepartment_name, e.evaluation_status " +
         "FROM EMPLOYEE e " +
         "LEFT JOIN EMPLOYEE_ROLE er USING (employeerole_id) " +
         "LEFT JOIN EMPLOYEE_STATUS es USING (employeestatus_id) " +
         "LEFT JOIN EMPLOYEE_LEVEL el USING (employeelevel_id) " +
         "LEFT JOIN EMPLOYEE_TYPE et USING (employeetype_id) " +
         "LEFT JOIN EMPLOYEE_DEPARTMENT ed USING (employeedepartment_id) ";
    
    public EmployeeBean getEmployee(int employee_id){
        try {
            String sql = null;
            
             sql = "SELECT e.employee_id, e.employee_name, e.employee_email, er.employeerole_name, es.employeestatus_name, " +
                    "el.employeelevel_name, et.employeetype_name, ed.employeedepartment_name, e.evaluation_status " +
                    "FROM EMPLOYEE e " +
                    "LEFT JOIN EMPLOYEE_ROLE er USING (employeerole_id) " +
                    "LEFT JOIN EMPLOYEE_STATUS es USING (employeestatus_id) " +
                    "LEFT JOIN EMPLOYEE_LEVEL el USING (employeelevel_id) " +
                    "LEFT JOIN EMPLOYEE_TYPE et USING (employeetype_id) " +
                    "LEFT JOIN EMPLOYEE_DEPARTMENT ed USING (employeedepartment_id)"
                     + "WHERE e.employee_id=" + employee_id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            String empname = rs.getString("employee_name");
            String empemail = rs.getString("employee_email");
            String emprole = rs.getString("employeerole_name");
            String empstatusname = rs.getString("employeestatus_name");
            String emplvlname = rs.getString("employeelevel_name");
            String emptypename = rs.getString("employeetype_name");
            String empdepname = rs.getString("employeedepartment_name");
            
            
            EmployeeBean empBean = new  EmployeeBean(employee_id, empname, empemail, emprole, empstatusname, emplvlname, emptypename, empdepname);
            con.close();
            return empBean;
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    
}
