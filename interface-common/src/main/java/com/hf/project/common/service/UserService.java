package com.hf.project.common.service;

import com.hf.project.common.entity.User;
import org.springframework.stereotype.Service;



public interface UserService {

    User getUser(String userCode);
}
