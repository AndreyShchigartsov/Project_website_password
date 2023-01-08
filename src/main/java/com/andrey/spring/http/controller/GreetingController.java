package com.andrey.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    @GetMapping("/hello")
    public String hello(ModelAndView modelAndView){

        return "greeting/hello";
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView modelAndView){
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }
}