
package com.example.shop2.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/catalog", "/product"})
public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession httpSession = req.getSession(false);
        if (httpSession != null && httpSession.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.sendRedirect("login?error=true");
        }

    }
    public void destroy() {
    }
}
