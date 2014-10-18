package com.dood.web.controller;

import com.dood.app.entities.User;
import com.dood.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Default mapping
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
//        ModelAndView mv= new ModelAndView("users");

        List<User> users = userService.findAll();
        model.addAttribute("user", new User());
        model.addAttribute("users", users);
//        mv.setViewName("users");
        return "users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        userService.createUser(user);
        return "redirect:/user/";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteByUserId(userId);
        return "redirect:/user/";
    }
}
