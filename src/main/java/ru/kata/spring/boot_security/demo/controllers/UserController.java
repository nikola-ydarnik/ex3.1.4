package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String adminInfo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user";
    }
}
