package ru.hh.school.social.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.hh.school.social.entities.User;
import ru.hh.school.social.entities.UserInfo;
import ru.hh.school.social.forms.SearchForm;
import ru.hh.school.social.services.FormProcessor;
import ru.hh.school.social.services.UserService;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 09.01.12
 * Time: 8:11
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SearchController {
    private final FormProcessor formProcessor;
    private final UserService userService;
    @Autowired
    public SearchController(FormProcessor formProcessor, UserService userService) {
        this.formProcessor = formProcessor;
        this.userService = userService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String initSearchForm(Model model){
        if (!userService.isAuth()){
            model.addAttribute("message","Access denied.\n");
            return "login";
        }
        model.addAttribute("searchForm",new SearchForm());
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String processSearchForm(Model model, @ModelAttribute("searchForm") SearchForm searchForm){
        if (!userService.isAuth()){
            model.addAttribute("message","Access denied.\n");
            return "login";
        }
        if (formProcessor.processSearch(model, searchForm)) {
            return "searchResults";
        } else {
            return "search";
        }
    }
}
