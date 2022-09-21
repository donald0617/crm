package com.shu.crm.workbench.web.controller;

import com.shu.crm.settings.domain.User;
import com.shu.crm.settings.service.UserService;
import com.shu.crm.settings.service.impl.UserServiceImpl;
import com.shu.crm.utils.MD5Util;
import com.shu.crm.utils.PrintJson;
import com.shu.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //响应中文字符串乱码问题
//        response.setContentType("text/html;charset=utf-8");

        String path = request.getServletPath();//获取请求路径

        if ("/workbench/activity/getUserList.do".equals(path)) {
            getUserList(request, response);
        }

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        //需要拿取到用户的信息，所以这里调用的还是 UserService
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        //调用方法获取用户列表
        List<User> uList = us.getUserList();
        //将用户对象转换成json
        PrintJson.printJsonObj(response,uList);


    }

}
