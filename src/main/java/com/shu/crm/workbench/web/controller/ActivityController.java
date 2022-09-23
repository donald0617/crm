package com.shu.crm.workbench.web.controller;

import com.shu.crm.settings.domain.User;
import com.shu.crm.settings.service.UserService;
import com.shu.crm.settings.service.impl.UserServiceImpl;
import com.shu.crm.utils.*;
import com.shu.crm.workbench.domain.Activity;
import com.shu.crm.workbench.service.ActivityService;
import com.shu.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

        if ("/workbench/activity/save.do".equals(path)) {
            save(request, response);
        }

    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("执行到 controller 的 save 方法");

        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        System.out.println(name);
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //创建时间应该为系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人为当前用户（从作用域中取）
        String createBy = ((User) request.getSession().getAttribute("user")).getName();

        //获取相关业务的代理对象
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);


        //调用方法 保存成功返回一个布尔值
        boolean flag = as.save(a);//此处需要将取得的数据放入方法中，这里采用将数据放入对象传入
        //将布尔值编译成 json 穿给前端
        PrintJson.printJsonFlag(response,flag);

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        //需要拿取到用户的信息，所以这里调用的还是 UserService
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        //调用方法获取用户列表
        List<User> uList = us.getUserList();

        //将用户对象转换成json（响应给前端的ajax）
        PrintJson.printJsonObj(response, uList);


    }

}
