package ru.kpfu.itis.filter;

import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/in/*")
public class UserFilter implements Filter {

    private final static String PAGE_USER_COOKIE_NAME = "pageUser";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        if (!processUserCookie(request)) {
            response.sendRedirect("/signIn");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private Boolean processUserCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(PAGE_USER_COOKIE_NAME)) {
                return true;
            }
        }
        return false;
    }
}
