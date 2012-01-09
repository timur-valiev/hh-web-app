package ru.hh.school.social.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.hh.school.social.entities.UserInfo;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.services.FormProcessor;
import ru.hh.school.social.forms.LoginForm;
import ru.hh.school.social.forms.RegisterForm;
import ru.hh.school.social.services.UserFacade;
import ru.hh.school.social.services.UserService;

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
    private final UserService userService;
    private final UserFacade userFacade;
    @Autowired
    public UserController(UserFacade userFacade,FormProcessor formProcessor, UserService userService) {
        this.formProcessor = formProcessor;
        this.userService = userService;
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLoginForm(Model model, @ModelAttribute("message") String message){
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("message", message);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLoginForm(Model model, @ModelAttribute("loginForm") LoginForm loginForm){
        if (formProcessor.checkLoginForm(model, loginForm)) {
            userService.setAuth();
            return "redirect:/main_page";
        }
        else {
            return "login";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String initRegistrationForm(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationForm(Model model, @ModelAttribute("registerForm") RegisterForm registerForm)
    {
        if (formProcessor.register(model, registerForm)){
            return "redirect:/login";
        } else {
            return "register";
        }
    }

    @RequestMapping(value = "/main_page")
    public String mainPage(Model model){
        if (!userService.isAuth()){
            model.addAttribute("message","Access denied.\n");
            return "login";
        }
        return "main_page";
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model){
        if (userService.isAuth())
            userService.unsetAuth();
        return "redirect:/";
    }
    
    @RequestMapping(value = "/users/{id:[0-9]+}")
    public String userInfo(Model model, @PathVariable String id){
        try {
            UserInfo user = userFacade.getById(new Long(id));
            model.addAttribute("user",user);
        } catch (NoIdException e) {
            model.addAttribute("error","No user with id = "+ e.getId().toString());
        }
        return "userInfo";

    }
    
}
