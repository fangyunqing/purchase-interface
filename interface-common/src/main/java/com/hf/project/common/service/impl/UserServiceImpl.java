package com.hf.project.common.service.impl;

import com.hf.project.common.entity.User;
import com.hf.project.common.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description 用户服务
 * @Author fyq
 * @Date 2021/8/25 17:25
 **/

@Service
public class UserServiceImpl implements UserService {

    /**
     * @Author fyq
     * @Description 调用NC接口 获取用户信息  暂未开发
     * @Date 17:27 2021/8/25
     * @Param [userCode]
     * @return com.hf.project.common.entity.User
     **/
    @Override
    public User getUser(String userCode) {

        // test
        User user = new User();
        user.setUserCode(userCode);

        return user;

    }
}
