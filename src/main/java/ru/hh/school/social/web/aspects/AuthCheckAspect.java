package ru.hh.school.social.web.aspects;

import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.hh.school.social.impl.services.SessionService;
import ru.hh.school.social.web.SessionExtractor;
import ru.hh.school.social.web.forms.LoginForm;
import ru.hh.school.social.web.interceptors.SessionExtractionInterceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 14.01.12
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */

@Component
@Aspect
public class AuthCheckAspect {
    private final SessionExtractor sessionExtractor;
    @Autowired
    public AuthCheckAspect(SessionExtractor sessionExtractor) {
        this.sessionExtractor = sessionExtractor;
    }

    @Pointcut("@annotation(ru.hh.school.social.web.annotations.AuthRequired)")
    public void authRequiredMethod() { }

    @Around("authRequiredMethod()")
    public Object AuthCheck(ProceedingJoinPoint proceedingJoinPoint) {
        if (sessionExtractor.getCurrentSession() != null)
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {}

        return "redirect:/login";
    }
}
