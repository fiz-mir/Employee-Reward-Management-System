package Bean;

import java.io.Serializable;

public class ApplicationBean implements Serializable {

    private int Applicationform_id;
    private int Administrator_id;
    private int Employee_id;
    private int Job_Knowledge;
    private int Attitude_Environment;
    private int Quality_Work;
    private int Productivity_Task;
    private int Punctual_Attendance;
    private int Interpersonal_Skill;
    private int Initiative_Innovation;
    private int Customer_Service;
    private int Leadership_Teamwork;
    private int Adaptability_Flexibility;
    private int Categoryreward_id;
    private int Total_Mark;

    public ApplicationBean() {
        
    }

    public ApplicationBean(int Applicationform_id, int Administrator_id, int Employee_id, int Job_Knowledge, 
            int Attitude_Environment, int Quality_Work, int Productivity_Task, int Punctual_Attendance, int Interpersonal_Skill, 
            int Initiative_Innovation, int Customer_Service, int Leadership_Teamwork, 
            int Adaptability_Flexibility, int Categoryreward_id, int Total_Mark) {
        this.Applicationform_id = Applicationform_id;
        this.Administrator_id = Administrator_id;
        this.Employee_id = Employee_id;
        this.Job_Knowledge = Job_Knowledge;
        this.Attitude_Environment = Attitude_Environment;
        this.Quality_Work = Quality_Work;
        this.Productivity_Task = Productivity_Task;
        this.Punctual_Attendance = Punctual_Attendance;
        this.Interpersonal_Skill = Interpersonal_Skill;
        this.Initiative_Innovation = Initiative_Innovation;
        this.Customer_Service = Customer_Service;
        this.Leadership_Teamwork = Leadership_Teamwork;
        this.Adaptability_Flexibility = Adaptability_Flexibility;
        this.Categoryreward_id = Categoryreward_id;
        this.Total_Mark = Total_Mark;
    }

    public int getApplicationform_id() {
        return Applicationform_id;
    }

    public int getAdministrator_id() {
        return Administrator_id;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public int getJob_Knowledge() {
        return Job_Knowledge;
    }

    public int getAttitude_Environment() {
        return Attitude_Environment;
    }

    public int getQuality_Work() {
        return Quality_Work;
    }

    public int getProductivity_Task() {
        return Productivity_Task;
    }

    public int getPunctual_Attendance() {
        return Punctual_Attendance;
    }

    public int getInterpersonal_Skill() {
        return Interpersonal_Skill;
    }

    public int getInitiative_Innovation() {
        return Initiative_Innovation;
    }

    public int getCustomer_Service() {
        return Customer_Service;
    }

    public int getLeadership_Teamwork() {
        return Leadership_Teamwork;
    }

    public int getAdaptability_Flexibility() {
        return Adaptability_Flexibility;
    }

    public int getCategoryreward_id() {
        return Categoryreward_id;
    }

    public int getTotal_Mark() {
        return Total_Mark;
    }

    public void setApplicationform_id(int Applicationform_id) {
        this.Applicationform_id = Applicationform_id;
    }

    public void setAdministrator_id(int Administrator_id) {
        this.Administrator_id = Administrator_id;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public void setJob_Knowledge(int Job_Knowledge) {
        this.Job_Knowledge = Job_Knowledge;
    }

    public void setAttitude_Environment(int Attitude_Environment) {
        this.Attitude_Environment = Attitude_Environment;
    }

    public void setQuality_Work(int Quality_Work) {
        this.Quality_Work = Quality_Work;
    }

    public void setProductivity_Task(int Productivity_Task) {
        this.Productivity_Task = Productivity_Task;
    }

    public void setPunctual_Attendance(int Punctual_Attendance) {
        this.Punctual_Attendance = Punctual_Attendance;
    }

    public void setInterpersonal_Skill(int Interpersonal_Skill) {
        this.Interpersonal_Skill = Interpersonal_Skill;
    }

    public void setInitiative_Innovation(int Initiative_Innovation) {
        this.Initiative_Innovation = Initiative_Innovation;
    }

    public void setCustomer_Service(int Customer_Service) {
        this.Customer_Service = Customer_Service;
    }

    public void setLeadership_Teamwork(int Leadership_Teamwork) {
        this.Leadership_Teamwork = Leadership_Teamwork;
    }

    public void setAdaptability_Flexibility(int Adaptability_Flexibility) {
        this.Adaptability_Flexibility = Adaptability_Flexibility;
    }

    public void setCategoryreward_id(int Categoryreward_id) {
        this.Categoryreward_id = Categoryreward_id;
    }

    public void setTotal_Mark(int Total_Mark) {
        this.Total_Mark = Total_Mark;
    }

   
}
