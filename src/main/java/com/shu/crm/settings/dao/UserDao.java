package com.shu.crm.settings.dao;

import com.shu.crm.settings.domain.User;

import java.util.HashMap;

public interface UserDao {

    User login(HashMap<String, Object> map);
}
