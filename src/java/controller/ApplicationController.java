package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ApplicationBean;
import DAO.ApplicationDAO;

public class ApplicationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> msgs = new LinkedList<>();

        try {
            
            // Get Parameters
            String Employee_id = request.getParameter("Employee_id");
            String Job_Knowledge = request.getParameter("Job_knowledge");
            String Attitude_Environment = request.getParameter("Attitude_environment");
            String Quality_Work = request.getParameter("Quality_work");
            String Productivity_Task = request.getParameter("Productivity_task");
            String Punctual_Attendance = request.getParameter("Punctual_attendance");
            String Interpersonal_Skill = request.getParameter("Interpersonal_skill");
            String Initiative_Innovation = request.getParameter("Initiative_innovation");
            String Customer_Service = request.getParameter("Customer_service");
            String Leadership_Teamwork = request.getParameter("Leadership_teamwork");
            String Adaptability_Flexibility = request.getParameter("Adaptability_flexibility");
            String Categoryreward_id = request.getParameter("Categoryreward_id");
            String Total_Mark = request.getParameter("Totalmark");
            String Applicationform_id = request.getParameter("Applicationform_id");
            String Administrator_id = request.getParameter("Administrator_id");
            String operation = request.getParameter("operation");

            
            // Create Bean and DAO Object
            ApplicationBean appBean = new ApplicationBean();
            ApplicationDAO appDAO = new ApplicationDAO();
            
            // Store Data In Bean Object
            appBean.setJob_Knowledge(Integer.parseInt(Job_Knowledge));
            appBean.setAttitude_Environment(Integer.parseInt(Attitude_Environment));
            appBean.setQuality_Work(Integer.parseInt(Quality_Work));
            appBean.setProductivity_Task(Integer.parseInt(Productivity_Task));
            appBean.setPunctual_Attendance(Integer.parseInt(Punctual_Attendance));
            appBean.setInterpersonal_Skill(Integer.parseInt(Interpersonal_Skill));
            appBean.setInitiative_Innovation(Integer.parseInt(Initiative_Innovation));
            appBean.setCustomer_Service(Integer.parseInt(Customer_Service));
            appBean.setLeadership_Teamwork(Integer.parseInt(Leadership_Teamwork));
            appBean.setAdaptability_Flexibility(Integer.parseInt(Adaptability_Flexibility));
            appBean.setCategoryreward_id(Integer.parseInt(Categoryreward_id));
            appBean.setApplicationform_id(Integer.parseInt(Applicationform_id));
            appBean.setAdministrator_id(Integer.parseInt(Administrator_id));
            appBean.setEmployee_id(Integer.parseInt(Employee_id));   

            if (operation.equals("C")) {
                if (Employee_id == null || Employee_id.isEmpty() ||
                    Job_Knowledge == null || Job_Knowledge.isEmpty() ||
                    Attitude_Environment == null || Attitude_Environment.isEmpty() ||
                    Quality_Work == null || Quality_Work.isEmpty() ||
                    Productivity_Task == null || Productivity_Task.isEmpty() ||
                    Punctual_Attendance == null || Punctual_Attendance.isEmpty() ||
                    Interpersonal_Skill == null || Interpersonal_Skill.isEmpty() ||
                    Initiative_Innovation == null || Initiative_Innovation.isEmpty() ||
                    Customer_Service == null || Customer_Service.isEmpty() ||
                    Leadership_Teamwork == null || Leadership_Teamwork.isEmpty() ||
                    Adaptability_Flexibility == null || Adaptability_Flexibility.isEmpty() ||
                    Categoryreward_id == null || Categoryreward_id.isEmpty() ||
                    Total_Mark == null || Total_Mark.isEmpty() ||
                    Applicationform_id == null || Applicationform_id.isEmpty() ||
                    Administrator_id == null || Administrator_id.isEmpty()) {
                    
                    msgs.add("Values are null");
                    request.setAttribute("msgs", msgs);
                    RequestDispatcher rd = request.getRequestDispatcher("/application.jsp");
                    rd.forward(request, response);
                    return;
                }else{
                    appBean.setTotal_Mark(Integer.parseInt(Total_Mark));
                    appDAO.AddApplication(appBean);
                    request.setAttribute("msgs", "An application form is submitted successfully!");
                    RequestDispatcher rd = request.getRequestDispatcher("/list-of-application.jsp");
                    
                    rd.forward(request, response);
                    
                    
                }
            }
            
            else if (operation.equals("D")) {
                    int empid = appBean.getEmployee_id();
                    appDAO.deleteApplication(empid);
                    request.setAttribute("msgs", "An application form is deleted successfully!");
                    RequestDispatcher rd = request.getRequestDispatcher("/list-of-application.jsp");
                    rd.forward(request, response);  
               
            }

            else if (operation.equals("U")) {                                  
                    appBean.setTotal_Mark(Integer.parseInt(Total_Mark));
                    appDAO.updateApplication(appBean);
                    request.setAttribute("msgs", "An application form is updated successfully!");
                    RequestDispatcher rd = request.getRequestDispatcher("/list-of-application.jsp");
                    rd.forward(request, response);
            }

                                                              
        } catch (NumberFormatException ex) {
            request.setAttribute("msgs", "The application form failed to update! Please make any changes before clicking save");
            RequestDispatcher rd = request.getRequestDispatcher("/application-edit.jsp");
            rd.forward(request, response);
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
            return;
        }

        // If no forward or error response has been triggered, redirect to index.jsp
         RequestDispatcher rd = request.getRequestDispatcher("/application-edit.jsp");
         rd.forward(request, response);
    }
}
