package org.example.userregistration.controller;

import org.example.userregistration.model.UserModel;
import org.example.userregistration.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
        System.out.println("register request: " + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getUsername(), userModel.getPassword());
        return registeredUser == null ? "register_error" : "/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request: " + userModel);
        boolean authenticateUser = userService.authenticateUser(userModel.getUsername(), userModel.getPassword());
        if(authenticateUser) {
            model.addAttribute("userLogin", userModel.getUsername());
            return "/welcome";
        } else {
            return "login_error";
        }
    }

    @GetMapping("/logout")
    public String getLogoutPage(Model model) {
        model.addAttribute("logoutRequest", new UserModel());
        return "logout";
    }

    @PostMapping("/logout")
    public String logout(@ModelAttribute UserModel userModel) {
        System.out.println("logout request: " + userModel);
        return "logout";
    }

}