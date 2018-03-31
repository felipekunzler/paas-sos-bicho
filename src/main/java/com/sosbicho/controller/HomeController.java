package com.sosbicho.controller;

import com.sosbicho.domain.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping({"/login", "/signup"})
    public String login(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Valid UserDto userDto, Errors errors, Model model) {
        validateSignup(userDto, errors);
        if (errors.hasErrors()) {
            model.addAttribute("signup", true);
            return "login";
        }
        return "redirect:/";
    }

    private void validateSignup(UserDto userDto, Errors errors) {
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            errors.rejectValue("matchingPassword", "passwords.must.match", "passwords must match");
        }
    }

}
