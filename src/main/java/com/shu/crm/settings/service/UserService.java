package com.shu.crm.settings.service;

import com.shu.crm.exception.LoginException;
import com.shu.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
