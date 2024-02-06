package com.maya.controller;

import com.maya.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/users2/list"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().json("[{\"id\":1,\"username\":\"张三\"},{\"id\":2,\"username\":\"李四\"}]"));
    }

    @Test
    public void testGet() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/user2/get").param("id", "1"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"username\":\"username: 1\"}"));
    }
}
