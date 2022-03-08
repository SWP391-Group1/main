package com.management.clinic.filter;

import com.management.clinic.constants.SessionConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthenFilter implements Filter {
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        try {
            if (uri.contains(".css")
                    || uri.contains(".js")
                    || uri.contains(".jpg") || uri.contains(".jpeg")
                    || uri.contains(".png")
                    || uri.contains("sign-in")
                    || uri.contains("sign-up")
                    || uri.equalsIgnoreCase("/home")
                    || uri.contains("/news/details")
            ) {
                chain.doFilter(request, response);
                return;
            } else {
                HttpSession session = req.getSession();
                if (session.getAttribute(SessionConstant.USER_APP) == null) {
                    RequestDispatcher rd = req.getRequestDispatcher("/user/sign-in");
                    rd.forward(request, response);
                    return;
                }
                chain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

}
