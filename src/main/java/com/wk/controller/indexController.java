package com.wk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wk on 2017/6/20.
 */
@Controller
public class indexController {
    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("title", "欢迎");
        model.addAttribute("head", "this is a page for test");
        return "index";
    }
}
