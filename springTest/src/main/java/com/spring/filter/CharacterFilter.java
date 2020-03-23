package com.spring.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Description 乱码过滤器
 * @Author Mr.Li
 * @Date 2020/3/18 13:04
 */
public class CharacterFilter implements Filter{
    private static String  charset="UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        charset=filterConfig.getInitParameter("charset");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        request.setCharacterEncoding(charset);
        response.setContentType("text/html;charset="+charset);
        response.setCharacterEncoding(charset);
        filterChain.doFilter(new MyRequest(request),response);
    }

    @Override
    public void destroy() {

    }

    /**
    *@Description: 装饰者模式装饰request
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/18 13:35
    */
    class MyRequest extends HttpServletRequestWrapper{
        private HttpServletRequest request;
        public MyRequest(HttpServletRequest request){
            super(request);
            this.request=request;
        }

        @Override
        public String getParameter(String name) {
            String parameter = request.getParameter(name);
            if (parameter!=null||parameter.trim().equals(""))
                return null;
            if (!request.getMethod().equalsIgnoreCase("get"))
                return parameter;
            try {
                return new String(parameter.getBytes("ISO8859-1"),charset);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
