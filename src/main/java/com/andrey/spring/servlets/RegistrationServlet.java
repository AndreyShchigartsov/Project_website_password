package com.andrey.spring.servlets;

import com.andrey.spring.filter.dto.UserCreateDto;
import com.andrey.spring.database.entity.Role;
import com.andrey.spring.exeption.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.andrey.spring.service.UserService;
import com.andrey.spring.util.JspHelper;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var userDto = new CreateUserDto(
//                req.getParameter("name"),
//                req.getParameter("lastname"),
//                req.getParameter("password"),
//                req.getParameter("role")
//        );
        var userDto = UserCreateDto.builder()
//                .name(req.getParameter("name"))
                .login(req.getParameter("lastname"))
                .rawPassword(req.getParameter("password"))
//                .role(req.getParameter("role"))
                .build();

        try{
//            userService.create(userDto);
            resp.sendRedirect("/login");
        }catch (ValidationException exception){
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }

    }

}
