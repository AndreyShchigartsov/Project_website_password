package com.andrey.spring.servlets;

import com.andrey.spring.service.StorageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var id = Long.parseInt(req.getParameter("id"));
//        storageService.delete(id);
        resp.sendRedirect("/user/storage");
    }
}
