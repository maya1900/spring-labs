package com.maya.controller;

import com.maya.dto.UserAddDTO;
import com.maya.dto.UserUpdateDTO;
import com.maya.service.UserService;
import com.maya.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping("")
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
    @GetMapping("/{id}")
    public UserVO get(@PathVariable("id") Integer id) {
        return new UserVO().setId(id).setUsername("username: " + id);
    }

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public UserVO get2(@PathVariable("id") Integer id) {
        return UserService.get(id);
    }

    /**
     * 添加用户
     *
     * @param addDTO
     * @return
     */
    @PostMapping("")
    public Integer add(UserAddDTO addDTO) {
        Integer resultId = 1;
        return resultId;
    }

    /**
     * 更新用户
     * @param id
     * @param updateDTO
     * @return
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable("id") Integer id, UserUpdateDTO updateDTO) {
        updateDTO.setId(id);
        Boolean success = true;
        return success;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Integer id) {
        Boolean success = true;
        return success;
    }
}
