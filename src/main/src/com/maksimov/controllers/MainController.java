package com.maksimov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vladislav Maksimov
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public String getMainPage() {
        return "index";
    }
}
