package com.maya.controller;

import com.maya.constants.ServiceExceptionEnum;
import com.maya.core.vo.CommonResult;
import com.maya.dto.UserAddDTO;
import com.maya.dto.UserUpdateDTO;
import com.maya.exception.ServiceException;
import com.maya.service.UserService;
import com.maya.vo.UserVO;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获得指定用户编号的用户
     *
     * 提供不使用 CommonResult 包装
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get")
    public UserVO get(@RequestParam("id") Integer id) {
        // 查询并返回用户
        return new UserVO().setId(id).setUsername(UUID.randomUUID().toString());
    }

    /**
     * 获得指定用户编号的用户
     *
     * 提供使用 CommonResult 包装
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get2")
    public CommonResult<UserVO> get2(@RequestParam("id") Integer id) {
        // 查询用户
        UserVO user = new UserVO().setId(id).setUsername(UUID.randomUUID().toString());
        // 返回结果
        return CommonResult.success(user);
    }

    /**
     * 测试抛出 NullPointerException 异常
     */
    @GetMapping("/exception-01")
    public UserVO exception01() {
        throw new NullPointerException("没有粗面鱼丸");
    }

    /**
     * 测试抛出 ServiceException 异常
     */
    @GetMapping("/exception-02")
    public UserVO exception02() {
        throw new ServiceException(ServiceExceptionEnum.USER_NOT_FOUND);
    }

    @GetMapping("/do_something")
    public void doSomething() {
        logger.info("[doSomething]");
    }
}
