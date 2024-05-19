package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

import util.connectDB;
import Bean.ApplicationBean;
import java.sql.DriverManager;
import java.sql.Statement;

public class ApplicationDAO {
    
    public ApplicationDAO(){
        
    }
    //call db connection (static method) to connect to java db
    public Connection con = connectDB.createConnection();
    
    //To get current local date
    LocalDate currentDate = LocalDate.now();
    Date currentSqlDate = Date.valueOf(currentDate);
    
   public void AddApplication(ApplicationBean appBean) {
    // Store Data In Bean Object
    int job_knowledge = appBean.getJob_Knowledge();
    int attitude_environment = appBean.getAttitude_Environment();
    int quality_work = appBean.getQuality_Work();
    int productivity_task = appBean.getProductivity_Task();
    int punctual_attendance = appBean.getPunctual_Attendance();
    int interpersonal_skill = appBean.getInterpersonal_Skill();
    int initiative_innovation = appBean.getInitiative_Innovation();
    int customer_service = appBean.getCustomer_Service();
    int leadership_teamwork = appBean.getLeadership_Teamwork();
    int adaptability_flexibility = appBean.getAdaptability_Flexibility();
    int categoryrewardid = appBean.getCategoryreward_id();
    int totalmark = appBean.getTotal_Mark();
    int appformid = appBean.getApplicationform_id();
    int adminid = appBean.getAdministrator_id();
    int empid = appBean.getEmployee_id();

     String sql = "INSERT INTO application_form (applicationform_id, job_knowledge, attitude_environment, quality_work, productivity_task, punctual_attendance, " +
            "interpersonal_skill, initiative_innovation, customer_service, leadership_teamwork, adaptability_flexibility, totalmark, applicationform_date, " +
            "categoryreward_id, administrator_id, employee_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";// 16 values 
 

    try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ORION_ERMSDB", "root", "root");
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, appformid);
        ps.setInt(2, job_knowledge);
        ps.setInt(3, attitude_environment);
        ps.setInt(4, quality_work);
        ps.setInt(5, productivity_task);
        ps.setInt(6, punctual_attendance);
        ps.setInt(7, interpersonal_skill);
        ps.setInt(8, initiative_innovation);
        ps.setInt(9, customer_service);
        ps.setInt(10, leadership_teamwork);
        ps.setInt(11, adaptability_flexibility);
        ps.setInt(12, totalmark);
        ps.setDate(13, currentSqlDate);
        ps.setInt(14, categoryrewardid);
        ps.setInt(15, adminid);
        ps.setInt(16, empid);

        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + " row(s) inserted successfully.");
        
        // Update evaluation_status to true if insertion is successful
        if (rowsAffected > 0) {
            String updateSql = "UPDATE EMPLOYEE SET evaluation_status = true WHERE employee_id = ?";
            try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
                updatePs.setInt(1, empid);
                int updateRowsAffected = updatePs.executeUpdate();
                if (updateRowsAffected > 0) {
                    System.out.println("evaluation_status updated to true for employee with ID: " + empid);
                } else {
                    System.out.println("Failed to update evaluation_status for employee with ID: " + empid);
                }
            }
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
   
   public ApplicationBean getApplication(int employee_id){
       try{
           ApplicationBean object;
           
           String sql = "select * from application_form where employee_id=" + employee_id;
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(sql);
           rs.next();
           int adminid = rs.getInt("administrator_id");
           int appid = rs.getInt("applicationform_id");
           int totalmark = rs.getInt("totalmark");
           int job_knowledge = rs.getInt("job_knowledge");
           int attitude_environment = rs.getInt("attitude_environment");
           int quality_work = rs.getInt("quality_work");
           int productivity_task = rs.getInt("productivity_task");
           int punctual_attendance = rs.getInt("punctual_attendance");
           int interpersonal_skill = rs.getInt("interpersonal_skill");
           int initiative_innovation = rs.getInt("initiative_innovation");
           int customer_service = rs.getInt("customer_service");
           int leadership_teamwork = rs.getInt("leadership_teamwork");
           int adaptability_flexibility = rs.getInt("adaptability_flexibility");
           int categoryrewardId = rs.getInt("categoryreward_id");


            object = new ApplicationBean(appid,adminid,employee_id,job_knowledge,attitude_environment,quality_work,productivity_task,punctual_attendance,interpersonal_skill
            ,initiative_innovation,customer_service,leadership_teamwork,adaptability_flexibility,categoryrewardId,totalmark);
            
            con.close();
            return object;
           
       }catch(Exception ex){
           System.out.println(ex);
       }
       return null;
   }
   
    public void updateApplication(ApplicationBean bean) {
    int job_knowledge = bean.getJob_Knowledge();
    int attitude_environment = bean.getAttitude_Environment();
    int quality_work = bean.getQuality_Work();
    int productivity_task = bean.getProductivity_Task();
    int punctual_attendance = bean.getPunctual_Attendance();
    int interpersonal_skill = bean.getInterpersonal_Skill();
    int initiative_innovation = bean.getInitiative_Innovation();
    int customer_service = bean.getCustomer_Service();
    int leadership_teamwork = bean.getLeadership_Teamwork();
    int adaptability_flexibility = bean.getAdaptability_Flexibility();
    int categoryrewardid = bean.getCategoryreward_id();
    int totalmark = bean.getTotal_Mark();
    int appformid = bean.getApplicationform_id();

    String sql = "UPDATE application_form SET job_knowledge=?, attitude_environment=?, quality_work=?, productivity_task=?, "
            + "punctual_attendance=?, interpersonal_skill=?, initiative_innovation=?, customer_service=?, "
            + "leadership_teamwork=?, adaptability_flexibility=?, categoryreward_id=?, totalmark=? "
            + "WHERE applicationform_id = " + appformid;
    PreparedStatement ps;
    try {
        // Create prepared statement
        ps = con.prepareStatement(sql);
        ps.setInt(1, job_knowledge);
        ps.setInt(2, attitude_environment);
        ps.setInt(3, quality_work);
        ps.setInt(4, productivity_task);
        ps.setInt(5, punctual_attendance);
        ps.setInt(6, interpersonal_skill);
        ps.setInt(7, initiative_innovation);
        ps.setInt(8, customer_service);
        ps.setInt(9, leadership_teamwork);
        ps.setInt(10, adaptability_flexibility);
        ps.setInt(11, categoryrewardid);
        ps.setInt(12, totalmark);

        ps.executeUpdate();
        con.close();
    } catch (Exception ex) {
        System.out.println(ex);
    }
}

    
     public void deleteApplication(int empid) {
    String updateSql = "UPDATE employee SET evaluation_status = false WHERE employee_id = ?";
    String deleteSql = "DELETE FROM application_form WHERE employee_id = ?";
    try {
        Connection con = connectDB.createConnection(); // Establish the database connection
        
        // Update the evaluation_status column
        PreparedStatement updatePs = con.prepareStatement(updateSql);
        updatePs.setInt(1, empid);
        updatePs.executeUpdate();
        
        // Delete the application from application_form table
        PreparedStatement deletePs = con.prepareStatement(deleteSql);
        deletePs.setInt(1, empid);
        deletePs.executeUpdate();
        
        con.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

     

    
   
   
       
}
