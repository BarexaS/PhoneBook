package com.barexas.controller;

import com.barexas.entities.CustomUser;
import com.barexas.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private CustomUser testUser;

    @Before
    public void setUp() throws Exception {
        this.testUser = new CustomUser("Foo", "password", "Full name");
        when(userService.getUserByLogin("Foo")).thenReturn(this.testUser);
    }

    @Test
    public void signup() throws Exception {
        this.mockMvc.perform(post("/signup")).andExpect(status().is3xxRedirection());

        this.mockMvc.perform(post("/signup")
                .param("login","Foo")
                .param("password","password1")
                .param("fio","full name")).andExpect(redirectedUrl("/signup?error"));

        this.mockMvc.perform(post("/signup")
                .param("login","Fo")
                .param("password","password1")
                .param("fio","full name")).andExpect(redirectedUrl("/signup?error"));

        this.mockMvc.perform(post("/signup")
                .param("login","Фуу")
                .param("password","password1")
                .param("fio","full name")).andExpect(redirectedUrl("/signup?error"));

        this.mockMvc.perform(post("/signup")
                .param("login","Bar@")
                .param("password","password1")
                .param("fio","full name")).andExpect(redirectedUrl("/signup?error"));

        this.mockMvc.perform(post("/signup")
                .param("login","Bar")
                .param("password","pass")
                .param("fio","full name")).andExpect(redirectedUrl("/signup?error"));

        this.mockMvc.perform(post("/signup")
                .param("login","Bar")
                .param("password","pass")
                .param("fio","fio")).andExpect(redirectedUrl("/signup?error"));

        this.mockMvc.perform(post("/signup")
                .param("login","Bar")
                .param("password","password1")
                .param("fio","Full name")).andExpect(redirectedUrl("/"));
    }

}