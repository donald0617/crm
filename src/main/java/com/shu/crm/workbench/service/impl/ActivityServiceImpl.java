package com.shu.crm.workbench.service.impl;

import com.shu.crm.settings.dao.UserDao;
import com.shu.crm.utils.SqlSessionUtil;
import com.shu.crm.vo.PaginationVO;
import com.shu.crm.workbench.dao.ActivityDao;
import com.shu.crm.workbench.domain.Activity;
import com.shu.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public PaginationVO<Activity> pageList(HashMap<String, Object> map) {

        //取得total
        int total = activityDao.getTotalByCondition(map);

        //取得dataList
        List<Activity> dataList = activityDao.getActivityByCondition(map);

        //将total和datalist封装到vo中
        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setPageList(dataList);
        //将vo返回
        return vo;
    }
}
