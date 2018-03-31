package com.sosbicho.controller;

import com.sosbicho.domain.User;
import com.sosbicho.domain.UserDto;
import com.sosbicho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/login", "/signup"})
    public String login(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Valid UserDto userDto, Errors errors, Model model, HttpServletRequest req) throws ServletException {
        validateSignup(userDto, errors);
        if (errors.hasErrors()) {
            model.addAttribute("signup", true);
            return "login";
        }
        register(userDto);
        req.login(userDto.getUsername(), userDto.getPassword());
        return "redirect:/";
    }

    private void register(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getPassword());
        userRepository.save(user);
    }

    private void validateSignup(UserDto userDto, Errors errors) {
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            errors.rejectValue("matchingPassword", "passwords.must.match", "passwords must match");
        }
        if (userRepository.existsByUsernameIgnoreCase(userDto.getUsername())) {
            errors.rejectValue("username", "username.exists", "user has been taken");
        }
    }

}
