package com.shu.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        //过滤post请求中文乱码
        req.setCharacterEncoding("utf-8");
        //过滤响应中文乱码
        resp.setContentType("text/html;charset=utf-8");
        //请求放行
        chain.doFilter(req,resp);

        //下一步，web.xml 过滤器
        //注意！ 需要在 servlet 之前
    }
}
