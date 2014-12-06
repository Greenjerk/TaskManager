package com.codexsoft.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    @Email
    @NotEmpty(message = "{validation.not_empty}")
    private String email;
    @Size(min = 4, max = 20, message = "{validation.size}")
    @NotEmpty(message = "{validation.not_empty}")
    @Pattern(regexp = "((?=.*[a-z]).{6,20})",
            message = "{validation.password}")
    private String password;
    @Size(min = 4, max = 32, message = "{validation.size}")
    @NotEmpty(message = "{validation.not_empty}")
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
