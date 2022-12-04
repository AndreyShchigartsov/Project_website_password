package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UrlPath;

import java.io.IOException;
@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var language = req.getParameter("lang");
        req.getSession().setAttribute("lang",language);
        resp.setHeader(req.getRequestURI(), "/locale");
        var prevPage = req.getHeader("referer");
        var page = prevPage != null ? prevPage : UrlPath.LOGIN;
        resp.sendRedirect(page + "?lang=" + language);
    }
}
