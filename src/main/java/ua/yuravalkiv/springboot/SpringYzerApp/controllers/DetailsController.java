package ua.yuravalkiv.springboot.SpringYzerApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetailsController {
    @GetMapping("/logout")
    public String logout(){
        return "/auth/login";
    }
}
