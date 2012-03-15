package ru.hh.school.social.impl.entities;

import ru.hh.school.social.ddd.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 10.01.12
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public class Session extends Entity {
    Long userId;
    String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
