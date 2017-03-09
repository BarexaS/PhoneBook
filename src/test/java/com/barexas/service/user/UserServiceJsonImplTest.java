package com.barexas.service.user;

import com.barexas.entities.CustomUser;
import com.barexas.entities.PhoneInfo;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class UserServiceJsonImplTest {

    private UserService userService;
    private CustomUser testUser;
    @Before
    public void setUp() throws Exception {
        UserServiceJsonImpl.setJsonDirectory("testing_files/users");
        this.userService = new UserServiceJsonImpl(new GsonBuilder().create());
        this.testUser = new CustomUser("Foo", "password", "Full name");
    }

    @After
    public void tearDown() throws Exception {
        File root = new File("testing_files/users");
        Arrays.stream(root.listFiles()).forEach(file -> file.delete());
        root.delete();
        new File("testing_files").delete();
    }

    @Test
    public void addUser() throws Exception {
        File root = new File("testing_files/users");
        assertEquals(root.listFiles().length,0);
        userService.addUser(this.testUser);
        assertEquals(root.listFiles().length,1);
    }

    @Test
    public void getUserByLogin() throws Exception {
        assertNull(userService.getUserByLogin("Bar"));
        userService.addUser(this.testUser);
        assertNotNull(userService.getUserByLogin("Foo"));
        assertThat(userService.getUserByLogin("Foo")).isEqualToComparingFieldByField(this.testUser);
    }

    @Test
    public void updateUser() throws Exception {
        userService.addUser(this.testUser);
        assertEquals(userService.getUserByLogin("Foo").getBook().size(),0);
        this.testUser.addInfo(new PhoneInfo());
        userService.updateUser(this.testUser);
        assertEquals(userService.getUserByLogin("Foo").getBook().size(),1);
    }

}