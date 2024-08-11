package com.mycompany.webapplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("")
    public String ShowHomepage() {
        System.out.println("test main controller");
        return "index";
    }
}
