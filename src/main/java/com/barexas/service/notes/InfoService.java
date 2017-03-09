package com.barexas.service.notes;


import com.barexas.entities.PhoneInfo;

import java.util.Set;

public interface InfoService {
    Set<PhoneInfo> searchInfo(String onwerLogin, String pattern);
    void deleteInfo(String onwerLogin, long[] ids);
    void addInfo(String onwerLogin, PhoneInfo info);
    PhoneInfo getInfo(String onwerLogin, long id);
    void editInfo(String ownerLogin, PhoneInfo info);
}
