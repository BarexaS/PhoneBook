package com.barexas.controller;

import com.barexas.entities.CustomUser;
import com.barexas.entities.PhoneInfo;
import com.barexas.service.notes.InfoService;
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
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InfoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InfoService infoService;
    @MockBean
    private UserService userService;

    private CustomUser testUser;
    private PhoneInfo testInfo;

    @Before
    public void setUp() throws Exception {
        this.testUser = new CustomUser("Foo", "password", "Full name");
        this.testInfo = new PhoneInfo("lastName","firstName","middleName","mobileNumb","homeNumb","address","email");
        this.testInfo.setId(2);
        when(userService.getUserByLogin("Foo")).thenReturn(this.testUser);
    }

    @Test
    @WithMockUser(username = "Foo")
    public void delete() throws Exception {
        this.mockMvc.perform(post("/deleteInfo")).andExpect(status().isOk());
        verify(infoService, never()).deleteInfo("Foo", null);
        this.mockMvc.perform(post("/deleteInfo")
                .param("toDelete[]", "1,2"))
                .andExpect(status().isOk());
        verify(infoService).deleteInfo(eq("Foo"), any());
    }

    @Test
    @WithMockUser(username = "Foo")
    public void search() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/search")
                .param("pattern", "123"))
                .andExpect(status().isOk()).andReturn();
        verify(infoService).searchInfo("Foo","123");
        assertEquals(result.getModelAndView().getViewName(),"home");
    }

    @Test
    @WithMockUser(username = "Foo")
    public void editInfoGetRequest() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/edit_1"))
                .andExpect(status().isOk()).andReturn();
        verify(infoService).getInfo("Foo",1);
        assertEquals(result.getModelAndView().getViewName(),"edit");
        assertEquals(result.getModelAndView().getModel().get("phoneinfo").getClass(), PhoneInfo.class);
        PhoneInfo viewInfo = (PhoneInfo) result.getModelAndView().getModel().get("phoneinfo");
        assertEquals(viewInfo.getId(), 0);

        when(infoService.getInfo("Foo",2)).thenReturn(this.testInfo);
        result = this.mockMvc.perform(post("/edit_2"))
                .andExpect(status().isOk()).andReturn();
        verify(infoService).getInfo("Foo",2);
        viewInfo = (PhoneInfo) result.getModelAndView().getModel().get("phoneinfo");
        assertEquals(viewInfo.getId(), 2);
    }

    @Test
    public void editInfoPostRequest() throws Exception {
        infoConstuct("").andExpect(redirectedUrl("/signup?error"));

    }

    @Test
    public void addInfo() throws Exception {

    }

    private ResultActions infoConstuct(String url) throws Exception {
        return this.mockMvc.perform(post(url)
                .param("login","Foo"));
    }

}