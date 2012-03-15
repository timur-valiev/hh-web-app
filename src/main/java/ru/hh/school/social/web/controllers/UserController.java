package ru.hh.school.social.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hh.school.social.impl.entities.Session;
import ru.hh.school.social.impl.entities.UserInfo;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.web.annotations.AuthRequired;
import ru.hh.school.social.web.forms.PersonalInfoForm;
import ru.hh.school.social.web.helpers.FormProcessor;
import ru.hh.school.social.web.forms.LoginForm;
import ru.hh.school.social.web.forms.RegisterForm;
import ru.hh.school.social.impl.services.SessionService;
import ru.hh.school.social.impl.services.UserFacade;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 03.01.12
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class UserController {

    private final FormProcessor formProcessor;
    private final UserFacade userFacade;
    private final SessionService sessionService;
    @Autowired
    public UserController(SessionService sessionService, UserFacade userFacade,
                          FormProcessor formProcessor) {
        this.formProcessor = formProcessor;
        this.userFacade = userFacade;
        this.sessionService = sessionService;
    }

    /**
     * For CGLIB only
     */
    @Deprecated
    public UserController() {
        formProcessor = null;
        userFacade = null;
        sessionService = null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLoginForm(Model model, @ModelAttribute("message") String message){
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("message", message);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLoginForm(Model model, @ModelAttribute("loginForm") LoginForm loginForm,
                                   HttpServletResponse response){
        if (formProcessor.checkLoginForm(model, loginForm)) {
            Session session = sessionService.setAuth(userFacade.byEmail(loginForm.getEmail()).getId());
            response.addCookie(new Cookie("SESSIONID",session.getSessionId()));
            return "redirect:/main_page";
        }
        return "login";

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String initRegistrationForm(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationForm(Model model, @ModelAttribute("registerForm") RegisterForm registerForm) {
        if (formProcessor.register(model, registerForm)){
            return "redirect:/login";
        }
        return "register";
    }

    @RequestMapping(value = "/main_page")
    @AuthRequired
    public String mainPage(Model model, HttpServletRequest request){
        return "main_page";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        if (sessionService.isAuth(sessionId)){
            sessionService.unsetAuth(sessionId);
            response.addCookie(new Cookie("SESSIONID","0"));
        }
        return "redirect:/";
    }
    
    @RequestMapping(value = "/users/{id:[0-9]+}")
    @AuthRequired
    public String userInfo(Model model, @PathVariable String id, HttpServletRequest request){
        try {
            UserInfo user = userFacade.getById(new Long(id));
            model.addAttribute("user",user);
        } catch (NoIdException e) {
            model.addAttribute("error","No user with id = "+ e.getId().toString());
        }
        return "userInfo";
    }

    @RequestMapping(value = "/personal")
    @AuthRequired
    public String personal(Model model, HttpServletRequest request){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        try {
            model.addAttribute("user",userFacade.getById(sessionService.getSessionUser(sessionId)));
            return "personal";
        } catch (NoIdException e) {
            model.addAttribute("error","Problems");
            return "redirect:/main_page";
        }
    }

    @RequestMapping(value = "/personal/info",  method = RequestMethod.GET)
    @AuthRequired
    public String showPersonalInfo(Model model, HttpServletRequest request){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        try {
            model.addAttribute("infoForm",new PersonalInfoForm(userFacade.
                                getById(sessionService.getSessionUser(sessionId))));
            return "personalInfo";
        } catch (NoIdException e) {
            return "redirect:/personal";
        }
    }

    @RequestMapping(value = "/personal/info",  method = RequestMethod.POST)
    @AuthRequired
    public String processPersonalInfo(Model model, HttpServletRequest request,
                                      @ModelAttribute("infoForm") PersonalInfoForm info){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        try {
            formProcessor.processPersonalInfo(userFacade.getById(sessionService.getSessionUser(sessionId)),info);
            return "personalInfo";
        } catch (NoIdException e) {
            return "redirect:/personal";
        }
    }
}
