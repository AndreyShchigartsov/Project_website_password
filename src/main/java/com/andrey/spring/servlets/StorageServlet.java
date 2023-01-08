package com.andrey.spring.servlets;

import com.andrey.spring.filter.dto.StorageCreateDto;
import com.andrey.spring.filter.dto.UserReadDto;
import com.andrey.spring.exeption.ValidationException;
import com.andrey.spring.service.StorageService;
import com.andrey.spring.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.andrey.spring.util.JspHelper;

import java.io.IOException;
@WebServlet("/user/storage")
public class StorageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = (UserReadDto)req.getSession().getAttribute("user");
        var userId = session.getId();
//        var storageList = storageService.read(userId);
//        req.setAttribute("storages", storageList);
        req.getRequestDispatcher(JspHelper.getPath("storage"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = (UserReadDto)req.getSession().getAttribute("user");
//        var session = (CreateStorageDto)req.getSession().getAttribute("user");
        var userId = session.getId();
//        var userEntity = userService.userId(userId);

        var createStorageDto = StorageCreateDto.builder()
//                .userId(userEntity)
                .password(req.getParameter("password"))
                .website(req.getParameter("website"))
                .comment(req.getParameter("comment"))
                .build();
        try{
//            storageService.create(createStorageDto);
            resp.sendRedirect("/user/storage");
        }catch (ValidationException exception){
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
