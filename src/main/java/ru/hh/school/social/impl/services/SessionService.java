package ru.hh.school.social.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hh.school.social.impl.entities.Session;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.impl.repositories.SessionRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 10.01.12
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SessionService {
    private final SessionRepository sessions;
    @Autowired
    public SessionService(SessionRepository sessions) {
        this.sessions = sessions;
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() == null)
            return null;
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals(name))
                return cookie.getValue();
        }
        return null;
    }
    
    public boolean isAuth(String sessionId){
        try {
            if (sessionId == null)
                return false;
            sessions.bySessionId(sessionId);
            return true;
        } catch (NoIdException e) {
            return false;
        }
    }
    
    public Session getSession(String sessionId) {
        try {
            if (sessionId == null)
                return null;
            return sessions.bySessionId(sessionId);
        } catch (NoIdException e) {
            return null;
        }
    }

    public Session setAuth(Long userId) {
        Session session = new Session();
        session.setUserId(userId);
        sessions.put(session);
        return session;
    }

    public void unsetAuth(String sessionId) {
        try {
            if (!(sessionId == null))
                sessions.removeBySessionId(sessionId);
        } catch (NoIdException e) {}
    }

    public Long getSessionUser(String sessionId){
        try {
            if (sessionId == null)
                return null;
            return sessions.bySessionId(sessionId).getUserId();
        } catch (NoIdException e) {
            return null;
        }
    }

}
