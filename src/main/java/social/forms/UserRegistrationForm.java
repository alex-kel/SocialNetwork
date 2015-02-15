package social.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Alexander on 13.02.2015.
 */
public class UserRegistrationForm {

    @NotEmpty
    @Size(min = 4, max = 30, message = "From 4 to 30 symbols only")
    private String login;

    @NotEmpty
    @Size(min = 4, max = 30, message = "From 4 to 30 symbols only")
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    @Size(min = 2, max = 30, message = "From 2 to 30 symbols only")
    private String fullName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
