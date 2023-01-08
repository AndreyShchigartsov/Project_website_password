package com.andrey.spring.filter;

import com.andrey.spring.filter.dto.UserReadDto;
import com.andrey.spring.database.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.andrey.spring.util.UrlPath.*;

@WebFilter("/user/*")
public class AuthorizationFilter implements Filter {

    public static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, LOCALE);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) request).getRequestURI();
        if(isPublicPath(uri) || isUserLoggedIn(request)){
            chain.doFilter(request,response);
        }else{
//            ((HttpServletRequest)request).getSession().invalidate();
            ((HttpServletResponse)response).sendRedirect("/login");
        }
    }

    private boolean isUserLoggedIn(ServletRequest request) {
        var user = (UserReadDto)((HttpServletRequest) request).getSession().getAttribute("user");
//        return user != null;
        return user == null ? false : user.getRole() == Role.USER;
//        return user.getRole() == Role.USER;
//        return user != null && user.getRole() == Role.ADMIN;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

}
