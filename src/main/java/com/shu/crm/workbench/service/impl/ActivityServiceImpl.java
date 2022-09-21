package com.shu.crm.workbench.service.impl;

import com.shu.crm.settings.dao.UserDao;
import com.shu.crm.utils.SqlSessionUtil;
import com.shu.crm.workbench.dao.ActivityDao;
import com.shu.crm.workbench.domain.Activity;
import com.shu.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {
    //操控数据库 获取数据库对象
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
}
