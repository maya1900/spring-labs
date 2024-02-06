package com.maya.controller;

import com.maya.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users2")
public class UserController2 {

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping("/list")
    public List<UserVO> list() {
        List<UserVO> result = new ArrayList<>();
        result.add(new UserVO().setId(1).setUsername("张三"));
        result.add(new UserVO().setId(2).setUsername("李四"));
        return result;
    }

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/get")
    public UserVO get(@RequestParam("id") Integer id) {
        return new UserVO().setId(id).setUsername("username: " + id);
    }

    /**
     * 添加用户
     * @param addDTO
     * @return
     */
    @PostMapping("/add")
    public Integer add(UserVO addDTO) {
        Integer resultId = UUID.randomUUID().hashCode();
        return resultId;
    }

    /**
     * 更新用户
     * @param updateDTO
     * @return
     */
    @PostMapping("/update")
    public Boolean update(UserVO updateDTO) {
        return true;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam("id") Integer id) {
        return true;
    }
}
