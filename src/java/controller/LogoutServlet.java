package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // fetch session object
        
        if (session != null) {
            String adminID = (String) session.getAttribute("session_adminID"); // retrieve adminID from session
            
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ORION_ERMSDB", "root", "root");
                PreparedStatement ps = con.prepareStatement("UPDATE AUDIT_LOGIN SET auditlogin_out = ? WHERE administrator_id = ? AND auditlogin_out IS NULL");

                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String timestamp = dateTime.format(formatter);

                ps.setString(1, timestamp);
                ps.setString(2, adminID);

                // Execute the statement
                ps.executeUpdate();
                
                // Close resources
                ps.close();
                con.close();
            } 
            catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
            
            session.invalidate(); // remove all session attributes bound to session
            request.setAttribute("msgs", "You have logged out successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
            System.out.println("Logged out");
        }
    }
}
