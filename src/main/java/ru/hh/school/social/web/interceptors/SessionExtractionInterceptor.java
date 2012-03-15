package ru.hh.school.social.web.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.hh.school.social.impl.entities.Session;
import ru.hh.school.social.impl.services.SessionService;
import ru.hh.school.social.web.SessionExtractor;
import ru.hh.school.social.web.forms.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 21.01.12
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SessionExtractionInterceptor extends HandlerInterceptorAdapter implements SessionExtractor {
    private static ThreadLocal<Session> currentSession = new ThreadLocal<Session>(){
        public Session initialValue(){
            return null;
        }
    };

    private final SessionService sessionService;
    @Autowired
    public SessionExtractionInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        currentSession.set(sessionService.getSession(sessionId));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        currentSession.set(null);
    }

    @Override
    public Session getCurrentSession() {
        return currentSession.get();
    }
}
