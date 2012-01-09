package ru.hh.school.social.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 06.01.12
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class NoEmailException extends Exception  {

    private final String email;

    public NoEmailException(String email) {
        super(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
