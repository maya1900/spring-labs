package com.maya.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService1 {
    public void test(@NonNull String name, int age) {
        System.out.println("test");
    }
}
