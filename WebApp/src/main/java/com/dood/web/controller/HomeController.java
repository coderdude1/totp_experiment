package com.dood.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@RequestMapping("/")
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping(value="/", method = RequestMethod.GET)
    public String mainLanding() {
        return "index";
    }

//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
//                              @RequestParam(value = "logout", required = false) String logout) {
//        LOG.info("in default/login handler");
////        model.addAttribute("title", "Dashboard");
//        ModelAndView modelAndView = new ModelAndView();
//        if(!Strings.isNullOrEmpty(error)) {
//            modelAndView.addObject("error", "Invalid username or password");
//        }
//
//        if (!Strings.isNullOrEmpty(logout)) {
//            modelAndView.addObject("msg", "You have been successfully logged out");
//        }
//
//        modelAndView.setViewName("login");
////route to login
//        return modelAndView;
//    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboard(ModelMap modelMap) {
        LOG.info("in dashboard handler");
        modelMap.addAttribute("title", "Dashboard");

        return "index";
    }
}

