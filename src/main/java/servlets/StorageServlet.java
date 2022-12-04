package servlets;

import filter.dto.CreateStorageDto;
import filter.dto.ReadUserDto;
import exeption.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StorageService;
import service.UserService;
import util.JspHelper;

import java.io.IOException;
@WebServlet("/user/storage")
public class StorageServlet extends HttpServlet {

    private final StorageService storageService = StorageService.getInstance();
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = (ReadUserDto)req.getSession().getAttribute("user");
        var userId = session.getId();
        var storageList = storageService.read(userId);
        req.setAttribute("storages", storageList);
        req.getRequestDispatcher(JspHelper.getPath("storage"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = (ReadUserDto)req.getSession().getAttribute("user");
//        var session = (CreateStorageDto)req.getSession().getAttribute("user");
        var userId = session.getId();
        var userEntity = userService.userId(userId);
//        var createStorageDto = new CreateStorageDto(
//                userId,
//                req.getParameter("password"),
//                req.getParameter("website"),
//                req.getParameter("comment")
//        );
        var createStorageDto = CreateStorageDto.builder()
                .userId(userEntity)
                .password(req.getParameter("password"))
                .website(req.getParameter("website"))
                .comment(req.getParameter("comment"))
                .build();
        try{
            storageService.create(createStorageDto);
            resp.sendRedirect("/user/storage");
        }catch (ValidationException exception){
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
