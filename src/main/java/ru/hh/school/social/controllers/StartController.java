package ru.hh.school.social.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 03.01.12
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class StartController {
    @RequestMapping(value = "/")
    public String hello(Model model){
        return "index";
    }
}
