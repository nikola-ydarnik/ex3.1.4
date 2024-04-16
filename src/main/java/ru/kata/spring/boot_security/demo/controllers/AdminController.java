package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleSerivce;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final RoleSerivce roleSerivce;
    private final UserService userService;

    @Autowired
    public AdminController(RoleSerivce roleSerivce, UserService userService) {
        this.roleSerivce = roleSerivce;
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("roles", roleSerivce.findAll());
        model.addAttribute("users", userService. getListAllUsers());
        model.addAttribute("currentUser", user);
        model.addAttribute("userEmpty", new User());
        return "admin";
    }


    @PostMapping("/create")
    public String saveNewUser(@ModelAttribute("user") User user,
                              @RequestParam(value = "rolesForController", required = false) List<String>  rolesFromView) {
        userService.saveUser(user,  rolesFromView);
        return "redirect:/admin";
    }


    @PatchMapping(value ="/update/{id}")
    public String saveUpdateUser(@ModelAttribute("user") User user,
                                 @RequestParam(value = "rolesForController", required = false) List<String> rolesFromView) {

        System.out.println("........................................................");
        System.out.println("........................................................");
        System.out.println("........................................................");
        System.out.println(user);
        System.out.println(rolesFromView);
        userService.updateUser(user, rolesFromView);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id,
                             @ModelAttribute("user") User user) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}


