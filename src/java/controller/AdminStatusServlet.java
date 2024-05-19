package controller;

import java.io.IOException;
import util.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AdminStatusServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdminStatusServlet.class.getName());

    protected abstract int getNewAdminStatus(int adminStatus);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String adminID = request.getParameter("adminID");
        LOGGER.log(Level.INFO, "adminID: {0}", adminID);
        int adminStatus = Integer.parseInt(request.getParameter("adminStatusID"));
        LOGGER.log(Level.INFO, "adminStatus: {0}", adminStatus);

        // Update the admin status in the database
        try (Connection conn = connectDB.createConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE ADMINISTRATOR SET ADMINISTRATORSTATUS_ID = ? WHERE ADMINISTRATOR_ID = ?")) {

            int newAdminStatus = getNewAdminStatus(adminStatus);

            stmt.setInt(1, newAdminStatus);
            stmt.setString(2, adminID);

            int rowsUpdated = stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Rows updated: {0}", rowsUpdated);

            // Handle logout if the admin changed their own status
            if (adminID.equals(session.getAttribute("session_adminID"))) {
                session.invalidate();
                request.setAttribute("errMessage", "You have logged out successfully");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
                return;
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while updating the admin status", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Redirect back to the page where the change was made
        response.sendRedirect(request.getHeader("referer"));
    }
}
