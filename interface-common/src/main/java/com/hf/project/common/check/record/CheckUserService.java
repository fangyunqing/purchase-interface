package com.hf.project.common.check.record;

import com.hf.project.common.entity.User;
import com.hf.project.common.entity.enums.RecordType;
import com.hf.project.common.mapper.common.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 用户档案
 * @Author fyq
 * @Date 2021/11/23 8:50
 **/

@Service
public class CheckUserService extends AbstractCheckRecord<User> {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected User doCheck(String... args) {

        String workCode = args[0];
        return userMapper.query(workCode);

    }

    @Override
    protected RecordType getRecordType() {
        return RecordType.USER;
    }
}
