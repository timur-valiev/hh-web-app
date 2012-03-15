package ru.hh.school.social.web.forms;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 03.01.12
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class RegisterForm {

    private String email;
    private String password;
    private String fullName;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
