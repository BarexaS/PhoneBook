package com.barexas.service.notes;

import com.barexas.entities.CustomUser;
import com.barexas.entities.PhoneInfo;
import com.barexas.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoServiceImplTest {

    private static final int NUMBER_OF_INFOS = 1000;

    @MockBean(name = "userService")
    private UserService userService;

    @Autowired
    private InfoService infoService;

    private CustomUser testUser;

    @Before
    public void setUp(){
        this.testUser = new CustomUser("Foo", "password", "Full name");
        when(this.userService.getUserByLogin("Foo")).thenReturn(this.testUser);

    }

    @Test
    public void delegationTest() {
        CustomUser user = userService.getUserByLogin("Foo");
        assertThat(user).isEqualToComparingFieldByField(testUser);
    }

    @Test
    public void addInfo() throws Exception {
        assertThat(this.testUser.getBook().size()).isEqualTo(0);
        generateAndAddInfo();
        assertThat(this.testUser.getBook().size()).isEqualTo(NUMBER_OF_INFOS);
    }



    @Test
    public void searchInfo() throws Exception {
        generateAndAddInfo();
        assertThat(infoService.searchInfo("Foo","321").size()).isEqualTo(1);
        assertThat(infoService.searchInfo("Foo","name").size()).isEqualTo(NUMBER_OF_INFOS);
    }

    @Test
    public void deleteInfo() throws Exception {
        generateAndAddInfo();
        Set<PhoneInfo> forDelete = infoService.searchInfo("Foo","21");
        long[] ids = new long[forDelete.size()];
        Iterator<PhoneInfo> iterator = forDelete.iterator();
        int i=0;
        while (iterator.hasNext()){
            ids[i] = iterator.next().getId();
            i++;
        }
        infoService.deleteInfo("Foo",ids);
        assertThat(this.testUser.getBook().size()).isEqualTo(NUMBER_OF_INFOS-forDelete.size());

    }

    @Test
    public void getInfo() throws Exception {
        generateAndAddInfo();
        Set<PhoneInfo> temp = infoService.searchInfo("Foo","1");
        PhoneInfo info = temp.iterator().next();
        PhoneInfo result = infoService.getInfo("Foo", info.getId());
        assertThat(result).isEqualToComparingFieldByField(info);
    }

    @Test
    public void editInfo() throws Exception {

    }

    private void generateAndAddInfo() {
        for (int i=0; i<NUMBER_OF_INFOS; i++){
            PhoneInfo info = new PhoneInfo("lastName"+i,"firstName"+i,"middleName"+i,"mobileNumb"+i,"homeNumb"+i,"address"+i,"email"+i);
            infoService.addInfo("Foo",info);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                //NOP
            }
        }
    }

}