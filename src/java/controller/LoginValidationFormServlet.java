package controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

import Bean.LoginBean;
import Bean.AdminBean;
import DAO.LoginDAO;
import DAO.AdminDAO;


public class LoginValidationFormServlet extends HttpServlet {
    
    public LoginValidationFormServlet(){}
    List<String> msgs = new LinkedList<>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        //creating object LoginBean and AdminBean
        LoginBean loginBean = new LoginBean(); 
        AdminBean adminBean = new AdminBean();  
                           
        //Get adminID and Password
        String adminID = request.getParameter("adminid");
        String password = request.getParameter("adminpassword");
        
        //Set parameters
        loginBean.setAdminID(Integer.parseInt(adminID)); 
        loginBean.setPassword(password);
        adminBean.setAdmin_id(Integer.parseInt(adminID));
        
        //DAO objects
        LoginDAO obj = new LoginDAO();
        AdminDAO obj2 = new AdminDAO();
        
     
        String userValidate = obj.authenticateUser(loginBean); // Validate User
        
        int adminid = adminBean.getAdmin_id();
        
        String adRole = obj2.getAdminRole(adminid);
        
        if(userValidate.equals("SUCCESS")){
            //Add Session data into DB Audit_Login
            obj.AddLoginSession(adminid);
            
            HttpSession session=request.getSession(); //to register session
            session.setAttribute("session_adminID",adminID); // to set the attribute to the session
            session.setAttribute("session_adminRole",adRole); // to set the attribute to the session
            request.setAttribute("msgs", "Login successful!");
            
            RequestDispatcher rd = request.getRequestDispatcher ("/index.jsp"); //RequestDispatcher is used
            rd.forward(request, response);
        }else{
            request.setAttribute("msgs", userValidate);//returns Invalid user credentials (fail login)
            RequestDispatcher rd = request.getRequestDispatcher ("/login.jsp"); //RequestDispatcher is used
            rd.forward(request, response);
        }
        
        
    }

    

}