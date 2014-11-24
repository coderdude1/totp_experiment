package com.dood.web.controller;

import com.dood.app.entities.User;
import com.dood.app.multifactorauth.TwoFactorAuthIetfRfc6238Utils;
import com.dood.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/userprefs/")
public class UserPreferencesController {
    private static final Logger log = LoggerFactory.getLogger(UserPreferencesController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private TwoFactorAuthIetfRfc6238Utils twoFactorAuthIetfRfc6238Utils;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUserPrefs(ModelMap model, Principal principal) {
        String email = principal.getName();
        User user = userService.getByLogin(email);
        model.addAttribute("user", user);
        return "/user/userprefs";
    }

    @RequestMapping(value = "/generateTotpSecret", method = RequestMethod.GET)
    public String enableTwoFactorAuth(ModelMap model, Principal principal) {
        String secret = twoFactorAuthIetfRfc6238Utils.generateSharedSecret();
        String email = principal.getName();
        User user = userService.getByLogin(email);
        user.setTotpSecretDate(new Date());
        user.setTotpSecret(secret);
        user.setTwoFactorAuthEnabled(true);
        userService.updateUser(user);
        model.addAttribute("user", user);
        return "redirect:/userprefs/";
    }

    /**
     * At some point we may wanna pull this into a different rest endpoint
     * once I add similar functionality to admin screen
     */
//    @RequestMapping(value = "/generateNewTotpSecret", method = RequestMethod.GET)
//    public String generateNewTotpSecret(ModelMap model, Principal principal) {
//
//    }
}
