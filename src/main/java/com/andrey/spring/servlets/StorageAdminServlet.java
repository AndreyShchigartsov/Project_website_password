package com.andrey.spring.servlets;

import com.andrey.spring.database.entity.Role;
import com.andrey.spring.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.andrey.spring.util.JspHelper;

import java.io.IOException;

@WebServlet("/admin/storage")
public class StorageAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var usersDtoList = userService.findAllUsers(Role.USER);
//        req.setAttribute("usersDtoList", usersDtoList);
        req.getRequestDispatcher(JspHelper.getPath("admin"))
                .forward(req,resp);
    }
}
