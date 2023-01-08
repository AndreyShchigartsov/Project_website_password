package com.andrey.spring.servlets;

import com.andrey.spring.service.StorageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.andrey.spring.util.JspHelper;

import java.io.IOException;

@WebServlet("/admin/storage/*")
public class StorageAdminUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("adminStorageUser"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = Long.parseLong(req.getParameter("id"));
//        var storageList = storageService.read(id);
//        req.setAttribute("storages", storageList);
        doGet(req, resp);
    }
}
