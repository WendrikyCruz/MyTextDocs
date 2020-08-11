package com.MyTextDocs.MyTextDocs.Interceptor;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutorizadorInterceptor  extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String uri = request.getRequestURI();
        if( uri.endsWith("Login")   ||
                uri.contains("resources")) {
            return true;
        }


        if(request.getSession().getAttribute("usuarioLogado") != null){
            return true;
        }
        response.sendRedirect("Login");
        return false;
    }
}
