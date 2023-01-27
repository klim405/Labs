package json;

public class LoginStatus {
    Boolean authIsSuccess;
    Boolean wrongPassword;
    Boolean wrongLogin;

    public LoginStatus() {
        this.authIsSuccess = false;
        this.wrongPassword = true;
        this.wrongLogin = true;
    }

    public LoginStatus(Boolean authIsSuccess, Boolean wrongPassword, Boolean wrongLogin) {
        this.authIsSuccess = authIsSuccess;
        this.wrongPassword = wrongPassword;
        this.wrongLogin = wrongLogin;
    }

    public Boolean getAuthIsSuccess() {
        return authIsSuccess;
    }

    public Boolean getWrongPassword() {
        return wrongPassword;
    }

    public Boolean getWrongLogin() {
        return wrongLogin;
    }

    public void setAuthIsSuccess(Boolean authIsSuccess) {
        this.authIsSuccess = authIsSuccess;
    }

    public void setWrongPassword(Boolean wrongPassword) {
        this.wrongPassword = wrongPassword;
    }

    public void setWrongLogin(Boolean wrongLogin) {
        this.wrongLogin = wrongLogin;
    }
}
