package com.andrey.spring.http.controller;

import com.andrey.spring.database.entity.Role;
import com.andrey.spring.filter.dto.UserCreateDto;
import com.andrey.spring.filter.dto.UserReadDto;
import com.andrey.spring.service.UserService;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "authentication/login";
    }

//    Пост маппинг предоставляет Spring Security
//    @PostMapping("/login")
//    public void redirectUserOrAdmin(@ModelAttribute UserCreateDto userCreateDto){
//
//    }

    @GetMapping("/registration")
    public String viewRegistration(Model model,
                                   @ModelAttribute("user")  UserCreateDto user,
                                   @CurrentSecurityContext SecurityContext securityContext,
                                   @AuthenticationPrincipal UserDetails userDetails){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "authentication/registration";
    }

    @PostMapping("/registration")
//    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute @Validated UserCreateDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/registration";
        }
        userService.create(user);
        return "redirect:/login";
    }
}
