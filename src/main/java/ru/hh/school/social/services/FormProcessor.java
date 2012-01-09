package ru.hh.school.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.hh.school.social.entities.User;
import ru.hh.school.social.entities.UserInfo;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.forms.LoginForm;
import ru.hh.school.social.forms.RegisterForm;
import ru.hh.school.social.forms.SearchForm;
import ru.hh.school.social.services.UserFacade;
import ru.hh.school.social.exceptions.EmailAlreadyBoundException;
import ru.hh.school.social.exceptions.IncorrectPasswordException;
import ru.hh.school.social.exceptions.NoEmailException;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 06.01.12
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FormProcessor {
    private final UserService userService;
    private final UserFacade userFacade;
    @Autowired
    public FormProcessor(UserService userService,UserFacade userFacade) {
        this.userService  = userService;
        this.userFacade = userFacade;
    }

    public boolean checkLoginForm(Model model, LoginForm loginForm){
        try {
            return userService.checkPassword(loginForm.getEmail(),loginForm.getPassword());
        } catch (NoEmailException e) {
            model.addAttribute("error", "No email: " + e.getEmail());
            return false;
        } catch (IncorrectPasswordException e){
            model.addAttribute("error", "Incorrect password: " + e.getEmail()+e.getPassword());
            return false;
        }
    }

    public boolean checkUserInfoForm(){
        return true;
    }

    public boolean register(Model model, RegisterForm registerForm) {
        if (!isEmail(registerForm.getEmail())) {
            model.addAttribute("error", "Not correct email " );
            return false;
        }
        try {
            userService.registerUser(registerForm.getEmail(), registerForm.getPassword(), registerForm.getFullName());
            model.addAttribute("message","You are successfully registered!\n");
            return true;
        } catch (EmailAlreadyBoundException e) {
            model.addAttribute("error", "Email already bound: " + e.getEmail());
            return false;
        }
    }

    public boolean processSearch(Model model, SearchForm searchForm) {
        ArrayList<UserInfo> result= new ArrayList<UserInfo>();
        if (isEmail(searchForm.getQuery())){
            try {
                result.add(userFacade.getByEmail(searchForm.getQuery()));
                model.addAttribute("id",result.get(0).getId());
                model.addAttribute("result",result);
                return true;
            } catch (NoEmailException e) {
                model.addAttribute("message","No matches found.");
                return false;
            }
        }
        if (isNumber(searchForm.getQuery())){
            Long id = new Long(searchForm.getQuery());
            try {
                result.add(userFacade.getById(id));
                model.addAttribute("result",result);
                return true;
            } catch (NoIdException e) {
                model.addAttribute("message","No matches found.");
                return false;
            }
        }
        model.addAttribute("error","Incorrect query.");
        return false;

    }

    private boolean isEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9._]+@(.+\\.)+.+");
        return p.matcher(email).matches();
    }

    private boolean isNumber(String query) {
        Pattern p = Pattern.compile("[0-9]+");
        return p.matcher(query).matches();
    }
}
