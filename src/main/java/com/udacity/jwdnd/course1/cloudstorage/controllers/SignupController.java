package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.Attribute;

@RequestMapping("/signup")
@Controller
public class SignupController {

    @Autowired
    private UserService userService;
    @GetMapping()
    public String getSignUpPage(Model model)
    {

        return "signup";
    }

    @PostMapping()
    public String doSignUp(@ModelAttribute User user, Model model)
    {
        String signupError = null;
        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";

        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
            //System.out.println("signup successful");
            return "redirectlogin";
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }

}
