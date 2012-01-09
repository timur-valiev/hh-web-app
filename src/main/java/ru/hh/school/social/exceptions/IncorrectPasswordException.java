package ru.hh.school.social.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 06.01.12
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */
public class IncorrectPasswordException extends Exception {
    private final String password;
    private final String email;

    public IncorrectPasswordException(String email,String password) {
        super(email);
        this.email = email; 
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
