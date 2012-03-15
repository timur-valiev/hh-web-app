package ru.hh.school.social.web.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.hh.school.social.impl.entities.UserInfo;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.impl.services.UserFacade;
import ru.hh.school.social.impl.services.UserService;
import ru.hh.school.social.web.forms.LoginForm;
import ru.hh.school.social.web.forms.PersonalInfoForm;
import ru.hh.school.social.web.forms.RegisterForm;
import ru.hh.school.social.web.forms.SearchForm;
import ru.hh.school.social.exceptions.EmailAlreadyBoundException;
import ru.hh.school.social.exceptions.IncorrectPasswordException;
import ru.hh.school.social.exceptions.NoEmailException;

import java.util.ArrayDeque;
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
        @SuppressWarnings("unchecked")
        ArrayList<UserInfo>[] result= new ArrayList[5];
        for( int i = 0; i < 5; i++) {
            result[ i] = new ArrayList<UserInfo>();
        }
        Boolean matchFound = false;
        for (UserInfo info: userFacade.all()) {
            Integer rate = 0;
            String name = searchForm.getName();
            String mail = searchForm.getMail();
            String work = searchForm.getWork();
            String study = searchForm.getStudy();
            if (info.getFullName().equals(name)&&!name.equals(""))
                rate += 10;
            if (info.getEmail().equals(mail)&&!mail.equals(""))
                rate += 10;
            if (info.getFullName().contains(name)&&!name.equals(""))
                rate += 1;
            if (info.getEmail().contains(mail)&&!mail.equals(""))
                rate += 1;
            if (info.getStudyPlaces().contains(study)&&!study.equals(""))
                rate += 1;
            if (info.getWorkPlaces().contains(work)&&!work.equals(""))
                rate += 1;
            if (rate>9) {
                matchFound = true;
                result[0].add(info);
            } else
            if (rate>0){
                matchFound = true;
                result[rate].add(info);
            }
        }
        if (matchFound) {
            result[0].addAll(result[4]);
            result[0].addAll(result[3]);
            result[0].addAll(result[2]);
            result[0].addAll(result[1]);
            model.addAttribute("result",result[0]);
            return true;
        }
        model.addAttribute("error","No matches found.");
        return false;

    }

    public void processPersonalInfo(UserInfo user, PersonalInfoForm info) {
        user.setFullName(info.getFullName());
        user.setStudyPlaces(info.getStudyPlaces());
        user.setWorkPlaces(info.getWorkPlaces());
        user.setSomeInformation(info.getSomeInformation());
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
