package com.tuski.springsec.controller;

import com.tuski.springsec.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tuski.springsec.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = new UserInfo();
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createUser(@Valid UserInfo userInfo, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfoExist = userService.findUserByEmail(userInfo.getEmail());
        if (userInfoExist != null){
            bindingResult.rejectValue("email","error.userInfo","Email already exists");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        }else {
            userService.saveUser(userInfo);
            modelAndView.addObject("successMessage","UserInfo added");
            modelAndView.addObject("userInfo", new UserInfo());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @GetMapping("/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView= new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName","Welcome "+ userInfo.getLastName());
        modelAndView.addObject("adminMessage","Content Available only for admin");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @GetMapping("/user/home")
    public ModelAndView user(){
        ModelAndView modelAndView= new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName","Welcome "+ userInfo.getLastName());
        modelAndView.addObject("adminMessage","Content Available only for USER");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }

}
