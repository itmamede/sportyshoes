package com.company.sporty.controller;

import com.company.sporty.entity.Login;
import com.company.sporty.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "login")
    public String loginPage(Login login, Model model, HttpServletRequest req, HttpServletResponse response) {
        model.addAttribute("login", login);
        return "index";
    }
    @GetMapping(value = "signup")
    public String signUpPage(Login login, Model model) {
        model.addAttribute("login", login);
        return "signup";
    }
    @PostMapping(value = "signupindb")
    public String signUpIntoDb(Login login, Model model) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        String result = loginService.createUserAccount(login);
        System.out.println(result);
        return "signup";
    }
}
