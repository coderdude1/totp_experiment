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

import java.util.List;

/**
 * TODO split the admin functions into other areas, this will end up doing too much
 */
@Controller
@RequestMapping("/useradmin/")
public class UserAdministrationController {
    @Autowired
    private UserService userService;

    /**
     * Default mapping
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAll();
        model.addAttribute("user", new User());
        model.addAttribute("users", users);
        return "/admin/users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        userService.createUser(user);
        return "redirect:/useradmin/";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteByUserId(userId);
        return "redirect:/useradmin/";
    }
}
