package com.shu.crm.settings.web.controller;

import com.shu.crm.settings.domain.User;
import com.shu.crm.settings.service.UserService;
import com.shu.crm.settings.service.impl.UserServiceImpl;
import com.shu.crm.utils.MD5Util;
import com.shu.crm.utils.PrintJson;
import com.shu.crm.utils.ServiceFactory;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //响应中文字符串乱码问题
        response.setContentType("text/html;charset=utf-8");

        String path = request.getServletPath();//获取请求路径

        if ("/settings/user/login.do".equals(path)) {
            login(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到用户验证环节");

        response.setContentType("text/html;charset=utf-8");

        //接收前端ajax传过来的 登录 信息
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        //将密码转为密文格式
        loginPwd = MD5Util.getMD5(loginPwd);

        //接收id地址
        String ip = request.getRemoteAddr();
        System.out.println(ip);

        //动态代理
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try {
            //传入参数
            //调用service层方法 验证成功继续执行/验证失败抛出抛出异常
            User user = us.login(loginAct, loginPwd, ip);

            //将对象传入 session作用域中 前端就能拿到数据，因为是用户登录操作 所以保存在session域
            request.getSession().setAttribute("user", user);

            //登录成功 给前端返回 {"success":true}
            PrintJson.printJsonFlag(response, true);//这里传的就是json

        } catch (Exception e) {//捕获：如果52行出现异常
            e.printStackTrace();

            //登陆失败
            String msg = e.getMessage();

            //需要传递多个json时，有两种方式
            //1、使用map集合，再将其解析为json（复用率底）
            //2、创建Vo对象 存在对象中（反复使用）

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            map.put("msg", msg);

            PrintJson.printJsonObj(response, map);

        }
    }
}
