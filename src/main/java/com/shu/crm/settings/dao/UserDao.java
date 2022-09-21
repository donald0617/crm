package com.shu.crm.settings.dao;

import com.shu.crm.settings.domain.User;

import java.util.HashMap;
import java.util.List;

public interface UserDao {

    User login(HashMap<String, Object> map);

    List<User> getUserList();
}
