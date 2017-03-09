package com.barexas.service.notes;


import com.barexas.entities.CustomUser;
import com.barexas.entities.PhoneInfo;
import com.barexas.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private UserService userService;
    @Override
    public Set<PhoneInfo> searchInfo(String onwerLogin, String pattern) {
        Set<PhoneInfo> resultSet = new HashSet<>();
        CustomUser user = userService.getUserByLogin(onwerLogin);
        for (PhoneInfo info : user.getBook()) {
            if (info.getFirstName().toLowerCase().contains(pattern.toLowerCase())) {
                resultSet.add(info);
            } else {
                if (info.getLastName().toLowerCase().contains(pattern.toLowerCase())){
                    resultSet.add(info);
                }
            }
            if (info.getMobileNumb().contains(pattern)) {
                resultSet.add(info);
            } else {
                if (info.getHomeNumb().contains(pattern)){
                    resultSet.add(info);
                }
            }
        }
        return resultSet;
    }

    @Override
    public void deleteInfo(String onwerLogin, long[] ids) {
        CustomUser user = userService.getUserByLogin(onwerLogin);
        Set<PhoneInfo> setForDelete = new HashSet<>();
        for (PhoneInfo info : user.getBook()) {
            for (long id : ids) {
                if (info.getId() == id) {
                    setForDelete.add(info);
                }
            }
        }
        user.getBook().removeAll(setForDelete);
        userService.updateUser(user);
    }

    @Override
    public void addInfo(String ownerLogin, PhoneInfo info) {
        CustomUser user = userService.getUserByLogin(ownerLogin);
        info.setId(System.currentTimeMillis());
        user.addInfo(info);
        userService.updateUser(user);
    }

    @Override
    public PhoneInfo getInfo(String onwerLogin, long id) {
        CustomUser user = userService.getUserByLogin(onwerLogin);
        for (PhoneInfo info: user.getBook()){
            if (info.getId()==id) return info;
        }
        return null;
    }

    @Override
    public void editInfo(String ownerLogin, PhoneInfo info) {
        deleteInfo(ownerLogin,new long[]{info.getId()});
        addInfo(ownerLogin,info);
    }
}
