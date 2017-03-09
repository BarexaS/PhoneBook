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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private CustomUser testUser;

    @Before
    public void setup() {
        this.testUser = new CustomUser("Foo", "password", "Full name");
        when(userService.getUserByLogin("Foo")).thenReturn(this.testUser);
    }

    @Test
    @WithMockUser(username = "Foo")
    public void home() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        assertThat(result.getModelAndView().getViewName()).isEqualToIgnoringCase("home");
    }

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().is3xxRedirection());

        MvcResult result = this.mockMvc.perform(get("/login")).andReturn();
        assertThat(result.getModelAndView().getViewName()).isEqualToIgnoringCase("login");
        this.mockMvc.perform(formLogin("/j_spring_security_check").user("j_login","Foo").password("j_password","password")).andExpect(redirectedUrl("/"));
    }

    @Test
    public void signup() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/signup")).andReturn();
        assertThat(result.getModelAndView().getViewName()).isEqualToIgnoringCase("signup");
    }

}