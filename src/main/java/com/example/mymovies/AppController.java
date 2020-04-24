package com.example.mymovies;

import com.example.mymovies.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/")
    public String homePage(Model model) {
        return "index";
    }
}
