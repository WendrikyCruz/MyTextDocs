package com.MyTextDocs.MyTextDocs.Controller;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;


public class FiltroPaginas implements Filter {

    public FiltroPaginas(){}
    public void destroy(){}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       // HttpSession session = request.getSession();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        HttpSession sessao = httpServletRequest.getSession();
        if(sessao.getAttribute("usuarioLogado") != null || url.lastIndexOf("Login")>-1 || url.lastIndexOf("Login.html")>-1){
            chain.doFilter(request, response);
        }
        else{
            ((HttpServletResponse) response).sendRedirect("Login");
        }
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
