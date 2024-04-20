package com.Waterboi.API.controller;

import com.Waterboi.API.entity.AppuserProfile;
import com.Waterboi.API.exception.PasswordMismatchException;
import com.Waterboi.API.entity.Appuser;
import com.Waterboi.API.repository.AppuserProfileRepository;
import com.Waterboi.API.repository.AppuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AppController {
    @Autowired
    private AppuserRepository appuserRepository;
    @Autowired
    private AppuserProfileRepository appuserProfileRepository;

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
        if(validateEmail(username)) {
            try {
                if (validatePassword(password) && password.equals(passwordConfirm)) {
                    Appuser appuser = new Appuser(username, passwordEncoder.encode(password));
                    appuserRepository.save(appuser);
                    appuserProfileRepository.save(new AppuserProfile(appuser.getId()));
                    return "redirect:/login";
                } else {
                    throw new PasswordMismatchException("password: " + password + "passwordConfirm: " + passwordConfirm);
                }
            } catch (PasswordMismatchException e) {
                return "redirect:/register?error";
            }
        }
        return "redirect:/register?error";

    }

    public static boolean validateEmail(String email) {
        // Regular expression pattern for validating email addresses
        String pattern = "^[\\w.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9.-]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }
    public static boolean validatePassword(String password) {
        if(password.length() >= 8 && password.length() <= 16) {
            if (!password.isEmpty()) {
                boolean hasLetter = false;
                boolean hasDigit = false;
                for (char ch : password.toCharArray()) {
                    if (Character.isLetter(ch)) {
                        hasLetter = true;
                    } else if (Character.isDigit(ch)) {
                        hasDigit = true;
                    }
                }
                return hasLetter && hasDigit;
            }
        }
        return false;

    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
