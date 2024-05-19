package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.AdminBean;
import DAO.AdminDAO;


public class AddAdminController extends HttpServlet {

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
            List<String> msgs = new LinkedList<>();
            AdminBean adBean = new AdminBean(); // Create an AdminBean object
            
            //Get parameter from form
            String employeeIDParam = request.getParameter("employee_id");
            String adminIDParam = request.getParameter("administratorstatus_id");
            String operation = request.getParameter("operation");


            if (employeeIDParam != null && !employeeIDParam.isEmpty()) {
                int employeeId = Integer.parseInt(employeeIDParam);
                int adminId = Integer.parseInt(adminIDParam);
                
                //Set value to admin bean
                adBean.setEmployee_id(employeeId);
                adBean.setAdministratorstatus_id(adminId);
                             
                if(operation.equals("I")){
                        //DAO object for add new admin details in admin table
                        AdminDAO dao = new AdminDAO();
                        dao.addAdmin(adBean);
                        request.setAttribute("msgs", "Administrator added successfully!");
                        RequestDispatcher rd = request.getRequestDispatcher("/list-of-admin.jsp");
                        rd.forward(request, response);
                }               
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();           
        }
    }
}

