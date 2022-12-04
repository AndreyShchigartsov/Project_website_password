package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SmsService;

import java.io.IOException;

@WebServlet("/user/addSms")
public class AddSmsServlets extends HttpServlet {
    private final SmsService smsService = SmsService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var sms = req.getParameter("name");
        var chatTitle = req.getParameter("chatTitle");
        smsService.addSms(sms,chatTitle);
        super.doPost(req, resp);
    }
}
