package com.shu.crm.workbench.service;

import com.shu.crm.vo.PaginationVO;
import com.shu.crm.workbench.domain.Activity;

import java.util.HashMap;

public interface ActivityService {
    boolean save(Activity a);

    PaginationVO<Activity> pageList(HashMap<String, Object> map);
}
