package com.ins.sys.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("")
public class LoginController {

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "/login";

    }

    @RequestMapping("test")
    public ModelAndView test(ModelAndView modelAndView){
        modelAndView.addObject("name","name");
        return modelAndView;
    }

}
