package ru.hh.school.social.impl.entities;

import ru.hh.school.social.ddd.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 08.01.12
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
public class Recommendation extends Entity {
    private UserInfo sender;
    private UserInfo writer;
    private UserInfo subject;
    private Boolean isSelf;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getSender() {
        return sender;
    }

    public void setSender(UserInfo sender) {
        this.sender = sender;
    }

    public UserInfo getWriter() {
        return writer;
    }

    public void setWriter(UserInfo writer) {
        this.writer = writer;
    }

    public UserInfo getSubject() {
        return subject;
    }

    public void setSubject(UserInfo subject) {
        this.subject = subject;
    }

    public Boolean getSelf() {
        return isSelf;
    }

    public void setSelf(Boolean self) {
        isSelf = self;
    }
}
