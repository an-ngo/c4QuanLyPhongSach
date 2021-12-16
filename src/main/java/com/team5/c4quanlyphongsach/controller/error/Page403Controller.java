package com.team5.c4quanlyphongsach.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Page403Controller {
    @GetMapping("/khongcoquyen")
    public ModelAndView page403(){
        return new ModelAndView("/page403");
    }
}