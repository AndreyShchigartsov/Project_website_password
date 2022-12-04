package servlets;

import filter.dto.ReadUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/user/message")
public class MessagesServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (ReadUserDto)req.getSession().getAttribute("user");
        var userLogin = user.getUsername();
        var usersDtoList = userService.findAllUsersExceptForMyself(userLogin);
        req.setAttribute("usersDtoListExceptForMyself", usersDtoList);
        req.getRequestDispatcher(JspHelper.getPath("message"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
