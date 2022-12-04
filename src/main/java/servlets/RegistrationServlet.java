package servlets;

import filter.dto.CreateUserDto;
import entity.Role;
import exeption.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
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
        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .login(req.getParameter("lastname"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .build();

        try{
            userService.create(userDto);
            resp.sendRedirect("/login");
        }catch (ValidationException exception){
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }

    }

}
