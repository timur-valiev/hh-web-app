package ru.hh.school.social.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.hh.school.social.impl.entities.Recommendation;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.web.annotations.AuthRequired;
import ru.hh.school.social.web.forms.RecommendationWritingForm;
import ru.hh.school.social.web.forms.SearchForm;
import ru.hh.school.social.web.helpers.FormProcessor;
import ru.hh.school.social.impl.services.RecommendationsDAO;
import ru.hh.school.social.impl.services.SessionService;
import ru.hh.school.social.impl.services.UserFacade;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 09.01.12
 * Time: 8:10
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ConversationController {
    private final FormProcessor formProcessor;
    private final UserFacade userFacade;
    private final SessionService sessionService;
    private final RecommendationsDAO recommendationsDao;

    @Autowired
    public ConversationController(FormProcessor formProcessor, UserFacade userFacade,
                                  SessionService sessionService, RecommendationsDAO recommendationsDao) {
        this.formProcessor = formProcessor;
        this.userFacade = userFacade;
        this.sessionService = sessionService;
        this.recommendationsDao = recommendationsDao;
    }

    /**
     * For CGLIB only
     */
    @Deprecated
    public ConversationController() {
        this.formProcessor = null;
        this.userFacade = null;
        this.sessionService = null;
        this.recommendationsDao = null;
    }

    @RequestMapping(value = "/personal/recommendations")
    @AuthRequired
    public String personalRecommendations(Model model, HttpServletRequest request){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        model.addAttribute("rec", recommendationsDao.
                            getSelfRecommendations(sessionService.getSessionUser(sessionId)));
        model.addAttribute("part","recommendations");
        model.addAttribute("act1","show");
        model.addAttribute("act2","delete");
        return "personalRecommendationList";
    }

    @RequestMapping(value = "/users/{id:[0-9]+}/recommendations")
    @AuthRequired
    public String userRecommendations(Model model, HttpServletRequest request, @PathVariable String id){
        model.addAttribute("rec", recommendationsDao.getSelfRecommendations(new Long(id)));
        model.addAttribute("id",id);
        return "userRecommendationList";
    }

    @RequestMapping(value = "/personal/inbox")
    @AuthRequired
    public String personalInbox(Model model, HttpServletRequest request){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        model.addAttribute("rec",recommendationsDao.getInbox(sessionService.getSessionUser(sessionId)));
        model.addAttribute("part","inbox");
        model.addAttribute("act1","show");
        model.addAttribute("act2","delete");
        return "personalRecommendationList";
    }

    @RequestMapping(value = "/personal/queries")
    @AuthRequired
    public String personalQueries(Model model, HttpServletRequest request){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        model.addAttribute("rec",recommendationsDao.getQueries(sessionService.getSessionUser(sessionId)));
        model.addAttribute("part","queries");
        model.addAttribute("act1","answer");
        model.addAttribute("act2","delete");
        return "personalRecommendationList";
    }

    @RequestMapping(value = "/users/request/{id:[0-9]+}")
    @AuthRequired
    public String requestMain(Model model, @PathVariable String id, HttpServletRequest request){
        model.addAttribute("id",id);
        return "requestDispatcher";
    }

    @RequestMapping(value = "/users/request/{id:[0-9]+}/about_me")
    @AuthRequired
    public String requestAboutMe(Model model, @PathVariable String id, HttpServletRequest request){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        Recommendation recommendation = new Recommendation();
        recommendation.setSelf(true);
        try {
            recommendation.setWriter(userFacade.getById(new Long(id)));
            recommendation.setSender(userFacade.getById(sessionService.getSessionUser(sessionId)));
            recommendation.setSubject(userFacade.getById(sessionService.getSessionUser(sessionId)));
            recommendationsDao.putQuery(recommendation);
            model.addAttribute("message","Your request sent.");
        } catch (NoIdException e) {
            model.addAttribute("message","Some problems");
        }
        return "redirect:/main_page";
    }

    @RequestMapping(value = "/users/request/{id:[0-9]+}/about_another", method = RequestMethod.GET)
    @AuthRequired
    public String requestAboutAnother(Model model, @PathVariable String id, HttpServletRequest request){
        model.addAttribute("searchForm",new SearchForm());
        model.addAttribute("action","users/request/"+id+"/about_another");
        return "search";
    }

    @RequestMapping(value = "/users/request/{id:[0-9]+}/about_another", method = RequestMethod.POST)
    @AuthRequired
    public String processAboutAnother(Model model,
                                      @PathVariable String id,
                                      @ModelAttribute("searchForm") SearchForm searchForm,
                                      HttpServletRequest request){
        if (formProcessor.processSearch(model, searchForm)){
            model.addAttribute("req","from_"+id+"_about_");
            return "searchResults";
        }
        return "search";

    }

    @RequestMapping(value = "/users/request/from_{writerId:[0-9]+}_about_{subjectId:[0-9]+}")
    @AuthRequired
    public String setRequestAboutAnother(Model model,
                                         @PathVariable String writerId,
                                         @PathVariable String subjectId,
                                         HttpServletRequest request){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        Recommendation recommendation = new Recommendation();
        recommendation.setSelf(false);
        try {
            recommendation.setWriter(userFacade.getById(new Long(writerId)));
            recommendation.setSender(userFacade.getById(sessionService.getSessionUser(sessionId)));
            recommendation.setSubject(userFacade.getById(new Long(subjectId)));
            recommendationsDao.putQuery(recommendation);
            model.addAttribute("message","Your request sent.");
        } catch (NoIdException e) {
            model.addAttribute("message","Some problems.");
        }
        return "redirect:/main_page";
    }

    @RequestMapping(value = "/personal/{part}/show/{id:[0-9]+}")
    @AuthRequired
    public String personalRecommendationShow(Model model, HttpServletRequest request,
                                             @PathVariable String id,
                                             @PathVariable String part){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        if (recommendationsDao.checkAccessRights(sessionService.getSessionUser(sessionId), new Long(id), part)) {
            try {
                model.addAttribute("rec",recommendationsDao.byId(new Long(id)));
                model.addAttribute("back","/personal/"+part);
                return "showRecommendation";
            } catch (NoIdException e) {
                model.addAttribute("message","Some problems.");
            }
        }
        return "redirect:/personal/"+part;
    }

    @RequestMapping(value = "/personal/{part}/delete/{id:[0-9]+}")
    @AuthRequired
    public String personalRecommendationDelete(Model model, HttpServletRequest request,
                                               @PathVariable String id,
                                               @PathVariable String part){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        if (recommendationsDao.checkAccessRights(sessionService.getSessionUser(sessionId), new Long(id), part)){
            recommendationsDao.removeById(new Long(id));
        }
        return "redirect:/personal/"+part;
    }

    @RequestMapping(value = "/users/{userId:[0-9]+}/recommendations/show/{recId:[0-9]+}")
    @AuthRequired
    public String userRecommendationShow(Model model, HttpServletRequest request,
                                             @PathVariable String userId,
                                             @PathVariable String recId){
        if(recommendationsDao.existSelf(new Long(recId), new Long(userId))){
            try {
                model.addAttribute("rec",recommendationsDao.byId(new Long(recId)));
                model.addAttribute("back","/users/"+userId+"/recommendations/");
                return "showRecommendation";
            } catch (NoIdException e) {
                model.addAttribute("error","Some problems.");
            }
        }
        model.addAttribute("message", "No rec.\n");
        return "redirect:/users/"+userId;
    }

    @RequestMapping(value = "/personal/queries/answer/{id:[0-9]+}",  method = RequestMethod.GET)
    @AuthRequired
    public String writeAnswer(Model model, HttpServletRequest request,
                                         @PathVariable String id){
        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        try {
            if (recommendationsDao.checkAccessRights(sessionService.getSessionUser(sessionId), new Long(id), "queries")){
                model.addAttribute("recForm",new RecommendationWritingForm());
                model.addAttribute("subject", recommendationsDao.byId(new Long(id)));
                return "writeRecommendation";
            }
        } catch (NoIdException e) {
            model.addAttribute("message","Some problems.");
        }
        return "redirect:/personal/queries";
    }

    @RequestMapping(value = "/personal/queries/answer/{id:[0-9]+}",  method = RequestMethod.POST)
    @AuthRequired
    public String processAnswer(Model model, HttpServletRequest request,
                                @PathVariable String id,
                                @ModelAttribute("recForm") RecommendationWritingForm recForm){

        String sessionId = SessionService.getCookieValue(request, "SESSIONID");
        if (recommendationsDao.checkAccessRights(sessionService.getSessionUser(sessionId), new Long(id), "queries"))
            recommendationsDao.processAnswer(new Long(id),recForm.getMessage());
        return "redirect:/personal/queries";
    }
}
