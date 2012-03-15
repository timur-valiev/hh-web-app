package ru.hh.school.social.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 09.01.12
 * Time: 9:45
 * To change this template use File | Settings | File Templates.
 */
public class NoIdException extends Exception {

    private final Long id;

    public NoIdException(Long id) {
        super(id.toString());
        this.id = id;
    }

    public NoIdException(String id) {
        super(id);
        this.id = 0L;
    }

    public Long getId() {
        return id;
    }
}
