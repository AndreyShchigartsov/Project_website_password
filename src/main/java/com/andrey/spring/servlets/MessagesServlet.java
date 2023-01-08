package com.andrey.spring.servlets;

import com.andrey.spring.filter.dto.UserReadDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.andrey.spring.service.UserService;
import com.andrey.spring.util.JspHelper;

import java.io.IOException;

@WebServlet("/user/message")
public class MessagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserReadDto)req.getSession().getAttribute("user");
//        var userLogin = user.getUsername();
//        var usersDtoList = userService.findAllUsersExceptForMyself(userLogin);
//        req.setAttribute("usersDtoListExceptForMyself", usersDtoList);
        req.getRequestDispatcher(JspHelper.getPath("message"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
