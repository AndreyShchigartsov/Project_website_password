package com.andrey.spring.http.controller;

import com.andrey.spring.database.entity.Role;
import com.andrey.spring.database.entity.Storage;
import com.andrey.spring.filter.dto.StorageReadDto;
import com.andrey.spring.filter.dto.UserReadDto;
import com.andrey.spring.service.StorageService;
import com.andrey.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final StorageService storageService;

    @GetMapping("/users")
    public String adminUsersList(Model model){
        var allUsersRoleUSER = userService.findAllUsers(Role.USER);
        model.addAttribute("users", allUsersRoleUSER);
        return "admin/admin";
    }



    @PostMapping("/users/storage/{login}")
    public String adminUsersStorageList(@PathVariable String login, Model model){
        var user = userService.userLogin(login);
        var storage = storageService.read(user);
        model.addAttribute("storages", storage);
        return "admin/adminStorageUser";
    }
}
