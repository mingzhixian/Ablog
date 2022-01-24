package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "testFilter", urlPatterns = "/editor.html")
public class testFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("拦截");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
