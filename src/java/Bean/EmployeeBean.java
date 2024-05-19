package Bean;

import java.io.Serializable;


public class EmployeeBean implements Serializable {
    private int empID;
    private String empName;
    private String empEmail;
    private String empRole;
    private String empStatus;
    private String empLevel;
    private String emptype;
    private String empDepartment;
    
    public EmployeeBean(){

    }

    public EmployeeBean(int empID, String empName, String empEmail, String empRole, String empStatus, String empLevel, String emptype, String empDepartment) {
        this.empID = empID;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empRole = empRole;
        this.empStatus = empStatus;
        this.empLevel = empLevel;
        this.emptype = emptype;
        this.empDepartment = empDepartment;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpLevel() {
        return empLevel;
    }

    public void setEmpLevel(String empLevel) {
        this.empLevel = empLevel;
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }

  
    
}
