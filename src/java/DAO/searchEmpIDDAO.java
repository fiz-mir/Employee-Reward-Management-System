package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.connectDB;
import Bean.EmployeeBean;

public class searchEmpIDDAO {

    public searchEmpIDDAO() {

    }
       
   public EmployeeBean displaySearch(int empID) {

    EmployeeBean object = null;

    String query = "SELECT e.employee_id, e.employee_name, e.employee_email, er.employeerole_name, es.employeestatus_name,"
            + "el.employeelevel_name, et.employeetype_name, ed.employeedepartment_name, ad.administratorstatus_id, e.evaluation_status "
            + "FROM EMPLOYEE e " + "LEFT JOIN EMPLOYEE_ROLE er USING (employeerole_id) "
            + "LEFT JOIN EMPLOYEE_STATUS es USING (employeestatus_id) "
            + "LEFT JOIN EMPLOYEE_LEVEL el USING (employeelevel_id) "
            + "LEFT JOIN EMPLOYEE_TYPE et USING (employeetype_id) "
            + "LEFT JOIN EMPLOYEE_DEPARTMENT ed USING (employeedepartment_id) "
            + "LEFT JOIN ADMINISTRATOR ad USING (employee_id)"
            + "WHERE ad.employee_id IS NULL AND e.employee_id = ?";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        connection = connectDB.createConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, empID);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int employeeIdValue = resultSet.getInt("employee_id");
            String employeeName = resultSet.getString("employee_name");
            String employeeEmail = resultSet.getString("employee_email");
            String employeeRoleId = resultSet.getString("employeerole_name");
            String employeeStatusId = resultSet.getString("employeestatus_name");
            String employeeLevelId = resultSet.getString("employeelevel_name");
            String employeeTypeId = resultSet.getString("employeetype_name");
            String employeeDepartmentId = resultSet.getString("employeedepartment_name");

            object = new EmployeeBean(employeeIdValue, employeeName, employeeEmail, employeeRoleId,
                    employeeStatusId, employeeLevelId, employeeTypeId, employeeDepartmentId);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources in the reverse order of their creation
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    if (object != null) {
        return object;
    } else {
        return new EmployeeBean();
    }
}

}
