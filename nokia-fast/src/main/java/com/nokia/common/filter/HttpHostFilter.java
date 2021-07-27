package com.nokia.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebFilter(filterName = "HttpHostFilter")
public class HttpHostFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        StringBuffer url = request.getRequestURL();
        String s = request.getRequestURL().toString();
        // 头攻击检测  过滤主机名
        String requestHost = request.getHeader("host");
        if (requestHost != null && !checkBlankList(requestHost)) {
            response.setStatus(403);
            return;
        }
        filterChain.doFilter(request, response);

    }

    //判断主机是否存在白名单中
    private boolean checkBlankList(String host){
        if(host.contains("127.0.0.1")){//此处为自己网站的主机地址
            return true;
        }
        return false;
    }
}
