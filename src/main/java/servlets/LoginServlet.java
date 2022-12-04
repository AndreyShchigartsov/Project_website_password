package servlets;


import filter.dto.ReadUserDto;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("name"),req.getParameter("password"))
                .ifPresentOrElse(
                        user -> ofLoginSuccess(user,req,resp),
                        () ->onLoginFail(req,resp)
                );
    }

    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ofLoginSuccess(ReadUserDto user, HttpServletRequest req, HttpServletResponse resp) {
            req.getSession().setAttribute("user", user);
        try {
            if(user.getRole() == Role.USER){
                resp.sendRedirect("/user/storage");
            }
            if(user.getRole() == Role.ADMIN){
                resp.sendRedirect("/admin/storage");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
