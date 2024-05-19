package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.EmployeeBean;
import DAO.searchEmpIDDAO;


public class SearchEmployeeController extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
            List<String> msgs = new LinkedList<>();
            EmployeeBean empBean = new EmployeeBean(); // Create an EmployeeBean object
            
            //Get parameter from form
            String employeeIDParam = request.getParameter("Employee_id");        
            String empName = request.getParameter("Employee_name");
            String empEmail = request.getParameter("Employee_email");
            String empRole = request.getParameter("Employeerole_id");
            String empStatus = request.getParameter("Employeestatus_id");
            String empLevel = request.getParameter("Employeelevel_id");
            String empType = request.getParameter("Employeetype_id");
            String empDepartment = request.getParameter("Employeedepartment_id");


            if (employeeIDParam != null && !employeeIDParam.isEmpty()) {
                int employeeId = Integer.parseInt(employeeIDParam);

                // Set value to employee bean
                empBean.setEmpID(employeeId);
                empBean.setEmpName(empName);
                empBean.setEmpEmail(empEmail);
                empBean.setEmpRole(empRole);
                empBean.setEmpStatus(empStatus);
                empBean.setEmpLevel(empLevel);
                empBean.setEmptype(empType);
                empBean.setEmpDepartment(empDepartment);
               
                // DAO object for searching
                searchEmpIDDAO obj = new searchEmpIDDAO();
                EmployeeBean employee = obj.displaySearch(employeeId);
                
                
                if (employee != null) {
                    request.setAttribute("Employee", employee);
                    RequestDispatcher rd = request.getRequestDispatcher("/administrator-add.jsp");
                    rd.forward(request, response);
                } else {
                    msgs.add("Employee not found");

                    // Set error messages as request attribute
                    request.setAttribute("msgs ", msgs);

                    // Forward back to the form with error messages
                    RequestDispatcher rd = request.getRequestDispatcher("/administrator-add.jsp");
                    rd.forward(request, response);
                }
            } else {
                msgs .add("Employee ID is required"); // Add error message

                // Set error messages as request attribute
                request.setAttribute("msgs ", msgs );

                // Forward back to the form with error messages
                RequestDispatcher rd = request.getRequestDispatcher("/administrator-add.jsp");
                rd.forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle any other exceptions
        }
    }
}

