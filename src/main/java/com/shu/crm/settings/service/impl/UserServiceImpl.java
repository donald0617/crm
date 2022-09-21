package com.shu.crm.settings.service.impl;

import com.shu.crm.exception.LoginException;
import com.shu.crm.settings.dao.UserDao;
import com.shu.crm.settings.domain.User;
import com.shu.crm.settings.service.UserService;
import com.shu.crm.utils.DateTimeUtil;
import com.shu.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);


    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        //将传进来的参数放入map集合中
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        //将集合传给Dao层负责查询
        User user = userDao.login(map);

        System.out.println("进入业务逻辑");

        //验证账户密码
        if (user == null){
            throw new LoginException("账号密码错误");
        }

        //验证失效时间
        String expireTime = user.getExpireTime();
        String sysTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(sysTime)<0){
            throw new LoginException("时间已失效");
        }

        //验证账户锁定
        String lockState = user.getLockState();
        if ("0".equals(lockState)){
            throw new LoginException("账户已锁定");
        }

        //判断ip地址
        String allowIps = user.getAllowIps();
        if (!allowIps.contains(ip)){//包含此ip
            throw new LoginException("ip受限");
        }

        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> uList = userDao.getUserList();
        return uList;
    }
}
