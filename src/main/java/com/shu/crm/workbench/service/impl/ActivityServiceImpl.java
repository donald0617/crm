package com.shu.crm.workbench.service.impl;

import com.shu.crm.settings.dao.UserDao;
import com.shu.crm.utils.SqlSessionUtil;
import com.shu.crm.workbench.dao.ActivityDao;
import com.shu.crm.workbench.domain.Activity;
import com.shu.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {
    //操控数据库 获取数据库对象
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    @Override
    public boolean save(Activity a) {
        //这就是一个标记，用于返回是否添加成功
        boolean flag = true;

        //返回一个收到影响的条数
        int count = activityDao.save(a);

        if (count != 1) {
            flag = false;
        }

        return flag;
    }
}
