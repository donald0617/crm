package com.shu.crm.workbench.dao;

import com.shu.crm.workbench.domain.Activity;

import java.util.HashMap;
import java.util.List;

public interface ActivityDao {

    int save(Activity a);

    List<Activity> getActivityByCondition(HashMap<String, Object> map);

    int getTotalByCondition(HashMap<String, Object> map);
}
