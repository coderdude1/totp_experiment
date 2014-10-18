package com.dood.web.controller;

import com.dood.web.model.User;
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

    /**
     * Request mapping for user
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ModelAndView getUsersView() {
        ModelAndView mv= new ModelAndView("usersView");
        User user = new User();
        user.setId(1l);
        user.setEmail("blah@blah.blah");
        user.setFamilyName("shit");
        user.setFirstName("fuck");
        List<User> users = new ArrayList<User>();
        users.add(user);
        mv.addObject("usersModel", users);
        return mv;
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String someDefaultHandler(ModelMap model) {
        return "users";
    }
}
