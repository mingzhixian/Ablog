package controller;

import service.isAuth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authFilter",urlPatterns = {"/editor.html","/SaveArt.do","/DelArt.do","/editor.html/*","/GetZip.do"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (!isAuth.isAuth(httpServletRequest, httpServletResponse)) {
            System.out.println("no login");

        }else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

