package lab.vista.vistaweb.entity;

public class User {
    public int uId;
    public String uAccount;
    public String uPassword;
    public String uEmail;
    public String uRegisterTime;
    public int getuId() {
        return uId;
    }
    public void setuId(int uId) {
        this.uId = uId;
    }
    public String getuAccount() {
        return uAccount;
    }
    public void setuAccount(String uAccount) {
        this.uAccount = uAccount;
    }
    public String getuPassword() {
        return uPassword;
    }
    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }
    public String getuEmail() {
        return uEmail;
    }
    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }
    public String getuRegisterTime() {
        return uRegisterTime;
    }
    public void setuRegisterTime(String uRegisterTime) {
        this.uRegisterTime = uRegisterTime;
    }
    @Override
    public String toString() {
        return "User [u_id=" + uId + ", u_account=" + uAccount + ", u_password=" + uPassword + ", u_email=" + uEmail
                + ", u_register_time=" + uRegisterTime + "]";
    }
    
}
