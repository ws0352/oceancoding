package com.oceancoding.ws.ocean.filter;


import com.oceancoding.ws.ocean.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(filterName = "urlFilter", urlPatterns = "/api/*")
public class UrlFilter implements Filter {
    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器已经创建");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        logger.info("过滤器请求地址" + requestURI);
        if(!requestURI.contains("info")){
            servletRequest.getRequestDispatcher("/failed").forward(servletRequest, servletResponse);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("过滤器已经销毁");

    }
}
