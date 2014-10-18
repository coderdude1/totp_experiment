package com.dood.web.controller;

import com.dood.app.entities.User;
import com.dood.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
//todo experiment with different types, ie modelMap, MdoelAndView
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String defaultHandler() {
//        return listUsers();
//    }

    /**
     * Request mapping for user
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
//        ModelAndView mv= new ModelAndView("users");
//        User user = new User();
//        user.setId(1l);
//        user.setEmail("blah@blah.blah");
//        user.setFamilyName("shit");
//        user.setFirstName("fuck");
//        List<User> users = new ArrayList<User>();
//        users.add(user);
        List<User> users = userService.findAll();
        model.addAttribute("user", new User());
        model.addAttribute("users", users);
//        mv.setViewName("users");
        return "users";
    }

    @RequestMapping(value="/list/all", method = RequestMethod.GET)
    public String someDefaultHandler(ModelMap model) {
        return "users";
    }
}
