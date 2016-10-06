package com.maksimov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 06.10.16.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public String getMainPage() {
        return "index";
    }
}
