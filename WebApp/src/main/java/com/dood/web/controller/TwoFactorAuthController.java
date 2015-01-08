package com.dood.web.controller;

import com.dood.app.entities.User;
import com.dood.app.service.TotpService;
import com.dood.app.service.UserService;
import com.google.common.primitives.Ints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Date;

/**
 *
 */
@Controller
@RequestMapping("/twofactorauth")
public class TwoFactorAuthController {
    private static final Logger log = LoggerFactory.getLogger(TwoFactorAuthController.class);
    @Autowired
    UserService userService;
    @Autowired
    TotpService totpService;

//    @RequestMapping(value="/", method = RequestMethod.GET)
    @RequestMapping(method = RequestMethod.GET)
    public String defaultHandler(ModelMap model, Principal principal) {
        log.debug("setting up two factor auth input for user " + principal.getName());
        String email = principal.getName();
        User user = userService.getByLogin(email);
        model.addAttribute("user", user);//display personalized page
        return "/security/twoFactorEntry";//redirect to the page to allow entry for the two factor auth code
    }

    @RequestMapping(value = "/submitCode", method = RequestMethod.POST)
    public String submitCode(@ModelAttribute("authCode") String authCode, ModelMap model, Principal principal) {
        //get entry
        String email = principal.getName();
        User user = userService.getByLogin(email);
        String secret = user.getTotpSecret();
        Integer intAuthCode = Ints.tryParse(authCode);
        if(intAuthCode == null) {
            model.addAttribute("error", "Code must be a 6 digit numeric");
//            return "redirect:/security/twoFactorEntry";//adds attribute to url, get error about request resource is not available
            return "/security/twoFactorEntry";
        }
        boolean verifiedCode = totpService.verifyCode(secret, intAuthCode, new Date());
        if(verifiedCode)  {
            //TODO update db with fact that the user session is authed (need to create
            //TODO set a session var to show the user has succesffully 2 factor authed
            //
            //a filter to verify that all requests are validated (to prevent url hacking to
            //bypass the 2 factor auth
            return "index";//for now reutrn to main landing page, later look up if user had an url entered (might be in HttpRequest
        }

        //TODO verify session var for successful 2 factor auth is removed
        //TODO  track number of attempts, save it, if exceed threshold, disable account, email user?
        model.addAttribute("user", user);
        model.addAttribute("error", "Invalid code entered.  Please try again");
        //            return "redirect:/security/twoFactorEntry";//adds attribute to url, get error about request resource is not available

        return "/security/twoFactorEntry";//TODO this might need some tweeking so error shows up
    }
}
