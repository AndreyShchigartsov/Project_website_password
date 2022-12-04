package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StorageService;
import service.UserService;

import java.io.IOException;

@WebServlet("/user/delete")
public class DeleteServlet extends HttpServlet {
    private final StorageService storageService = StorageService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = Integer.parseInt(req.getParameter("id"));
        storageService.delete(id);
        resp.sendRedirect("/user/storage");
    }
}
