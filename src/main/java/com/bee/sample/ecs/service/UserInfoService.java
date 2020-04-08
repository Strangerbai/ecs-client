package com.bee.sample.ecs.service;

import com.bee.sample.ecs.controller.request.UserInfo;

public interface UserInfoService {

    boolean createUser(UserInfo userInfo);

    UserInfo getUserByToken(String token);

    UserInfo getUserByUsername(String userName);


}
