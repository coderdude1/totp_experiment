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
    public String defaultHandler(ModelMap model) {
        LOG.info("in default handler");
        model.addAttribute("title", "Dashboard");

        return "index";
    }
}
