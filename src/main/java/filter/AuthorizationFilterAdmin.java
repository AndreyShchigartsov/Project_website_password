package filter;

import filter.dto.ReadUserDto;
import entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static util.UrlPath.*;

@WebFilter("/admin/*")
public class AuthorizationFilterAdmin implements Filter {
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
        var user = (ReadUserDto)((HttpServletRequest) request).getSession().getAttribute("user");
//        return user != null;
        return user == null ? false : user.getRole() == Role.ADMIN;
//        return user.getRole() == Role.USER;
//        return user != null && user.getRole() == Role.ADMIN;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}
