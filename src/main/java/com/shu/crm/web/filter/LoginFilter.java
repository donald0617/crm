package com.shu.crm.web.filter;

import com.shu.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入到验证没有通过登录的过滤器");

        //我们需要从session域中取出用户登录信息
        //但ServletRequest不是http协议，无法取到session 此时就需要强转为子类的HttpServlet
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取请求路径
        String path = request.getServletPath();
        if ("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)) {

            chain.doFilter(req, resp);
        } else {
            //从session作用域中取出登录对象user
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            //判断用户是否登录过
            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                System.out.println("11111");
                //没有登陆过 重定向到登陆页面
                //使用重定向而不使用转发的原因：
                //      重定向是重新发送请求url地址栏会刷新
                //      转发为一次请求，url地址不变，还是老的请求地址
                //request.getContextPath() 项目路径

                response.sendRedirect(request.getContextPath() +"/login.jsp");
                //这里的过滤器拦截了所有jsp与.do 包括login.jsp本身 这是一个死循环
                //我们需要单独放行login.jsp 与 登录验证 这两个请求
            }
        }
    }
}
