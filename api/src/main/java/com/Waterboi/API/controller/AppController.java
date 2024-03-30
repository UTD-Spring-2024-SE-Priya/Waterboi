package com.waterboi.api.controller;

import com.Waterboi.API.exception.PasswordMismatchException;
import com.waterboi.api.model.Appuser;
import com.waterboi.api.repository.AppuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private AppuserRepository appuserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/")
    public String index() {
        return "redirect:/register";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/register")
    public String processRegistration(String username, String password, String passwordConfirm) throws Exception{
        try {
            if(password.equals(passwordConfirm)) {
                Appuser appuser = new Appuser(username, passwordEncoder.encode(password));
                appuserRepository.save(appuser);
                return "redirect:/login";
            } else {throw new PasswordMismatchException("password: " + password + "passwordConfirm: " + passwordConfirm);}
        } catch (PasswordMismatchException e) {
            return "redirect:/register?error";
        }

    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
