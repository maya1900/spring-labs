package com.maya.service;

import com.maya.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static UserVO get(Integer id) {
        return new UserVO().setId(id).setUsername("test");
    }
}
