package Bean;


public class LoginBean {
    private int adminID;
    private String password;
    private String adminStatus;
    
    public LoginBean(){
        
    }
    
    public LoginBean(int adminID, String password, String adminStatus){
        this.adminID=adminID;
        this.password=password;
        this.adminStatus=adminStatus;
    }

    public int getAdminID() {
        return adminID;
    }

    public String getPassword() {
        return password;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }
   
}
