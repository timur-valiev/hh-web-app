package ru.hh.school.social.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.hh.school.social.web.annotations.AuthRequired;
import ru.hh.school.social.web.forms.SearchForm;
import ru.hh.school.social.web.helpers.FormProcessor;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    public SearchController(FormProcessor formProcessor) {
        this.formProcessor = formProcessor;
    }

    /**
     * For CGLIB only
     */
    @Deprecated
    public SearchController() {
        this.formProcessor = null;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @AuthRequired
    public String initSearchForm(Model model, HttpServletRequest request){
        model.addAttribute("searchForm",new SearchForm());
        model.addAttribute("action","search");
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @AuthRequired
    public String processSearchForm(Model model,
                                    @ModelAttribute("searchForm") SearchForm searchForm,
                                    HttpServletRequest request){
        if (formProcessor.processSearch(model, searchForm)) {
            return "searchResults";
        }
        return "search";
    }
}
