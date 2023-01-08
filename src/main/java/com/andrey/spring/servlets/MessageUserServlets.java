package com.andrey.spring.servlets;

import com.andrey.spring.filter.dto.UserReadDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.andrey.spring.service.ChatService;
import com.andrey.spring.service.SmsService;
import com.andrey.spring.util.JspHelper;

import java.io.IOException;

@WebServlet("/user/message/*")
public class MessageUserServlets extends HttpServlet {

    private final ChatService chatService = ChatService.getInstance();
    private final SmsService smsService = SmsService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("messageUser"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserReadDto)req.getSession().getAttribute("user");
//        var login1 = user.getUsername();
        var login2 = req.getParameter("login");

//        var chatTitle = chatService.findChat(login1,login2);
//        if(chatTitle.isPresent()){
//            var listSms = smsService.findSms(chatTitle.get());
//            req.setAttribute("chatTitle", chatTitle.get().getTitle());
//            req.setAttribute("listSms", listSms);
//        }else{
//            chatService.createChat(login1,login2);
//        }
//        doGet(req,resp);
    }
}
