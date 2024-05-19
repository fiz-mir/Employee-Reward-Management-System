package Bean;


public class AdminBean {
    private int administratorstatus_id;
    private int administratorrole_id;
    private int employee_id;
    private int admin_id;
    
    public AdminBean(){
//        administratorstatus_id = 50;
        administratorrole_id = 61;
    }

    public AdminBean(int administratorstatus_id, int administratorrole_id, int employee_id, int admin_id) {
        this.administratorstatus_id = administratorstatus_id;
        this.administratorrole_id = administratorrole_id;
        this.employee_id = employee_id;
        this.admin_id = admin_id;
    }

    public int getAdministratorstatus_id() {
        return administratorstatus_id;
    }

    public int getAdministratorrole_id() {
        return administratorrole_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdministratorstatus_id(int administratorstatus_id) {
        this.administratorstatus_id = administratorstatus_id;
    }

    public void setAdministratorrole_id(int administratorrole_id) {
        this.administratorrole_id = administratorrole_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    
    
    
    
}
