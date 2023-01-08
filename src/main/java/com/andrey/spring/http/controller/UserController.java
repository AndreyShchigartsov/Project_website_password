package com.andrey.spring.http.controller;

import com.andrey.spring.database.entity.Role;
import com.andrey.spring.filter.dto.*;
import com.andrey.spring.service.StorageService;
import com.andrey.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final StorageService storageService;
    private final UserService userService;



    @GetMapping("/storage")
    public String viewStorage(Model model,
                              @AuthenticationPrincipal UserDetails userDetails,
                              StorageFilter filter,
                              Pageable pageable){
        var roleUser = userDetails.getAuthorities().stream().toList().get(0).getAuthority();
        if(roleUser.equals(Role.ADMIN.getAuthority())){
            return "redirect:/admin/users";
        }
        var username = userDetails.getUsername();
        var user = userService.userLogin(username);
        var page = storageService.read(filter, pageable);
        model.addAttribute("userId", user.getId());
        model.addAttribute("storages", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "users/storage";
    }

    @PostMapping("/storage")
    public String addStorage(@ModelAttribute StorageCreateDto storageCreateDto){
        storageService.create(storageCreateDto);
        return "redirect:/users/storage";
    }

//    @DeleteMapping("storage/{id}")
    @PostMapping ("/storage/delete/{id}")
    public String deleteStorage(@PathVariable("id") Long id){
        storageService.delete(id);
        return "redirect:/users/storage";
    }

    @GetMapping ("/message")
    public String usersAllList(@AuthenticationPrincipal UserDetails userDetails, Model model){
        var users = userService.findAllUsersExceptForMyself(userDetails.getUsername());
        model.addAttribute("users", users);
        return "users/message";
    }
}
