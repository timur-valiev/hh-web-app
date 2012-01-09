package ru.hh.school.social.entities;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 08.01.12
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
public class Recommendation extends Entity{
    private Long sender;
    private Long writer;
    private Long subject;
    private Boolean isSelf;
    private Boolean isAccepted;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getWriter() {
        return writer;
    }

    public void setWriter(Long writer) {
        this.writer = writer;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public Boolean getSelf() {
        return isSelf;
    }

    public void setSelf(Boolean self) {
        isSelf = self;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }
}
