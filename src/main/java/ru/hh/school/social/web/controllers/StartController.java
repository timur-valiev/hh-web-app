package ru.hh.school.social.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hh.school.social.impl.services.SessionService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 03.01.12
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class StartController {
    private final SessionService sessionService;

    @Autowired
    public StartController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "/")
    public String hello(HttpServletRequest request){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        if (sessionService.isAuth(sessionId))
            return "main_page";
        return "index";
    }
}
