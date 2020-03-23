package com.spring.filter;

import com.spring.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/18 14:18
 */
@WebFilter(filterName = "TransactionFilter",urlPatterns = {"/*"})
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            MybatisUtils.commitTransaction();//提交事务
        }catch (Exception e){
            MybatisUtils.rollbackTransaction();
            MybatisUtils.commitTransaction();
        }finally {
            MybatisUtils.closeSqlSession();
        }
    }

    @Override
    public void destroy() {

    }
}
