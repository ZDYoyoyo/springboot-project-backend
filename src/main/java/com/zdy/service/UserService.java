package com.zdy.service;

import com.zdy.pojo.User;

public interface UserService {
    // 根據使用者名稱查詢使用者
    User findByUserName(String username);

    // 註冊
    void register(String username, String password);

    // 更新
    void update(User user);

    // 更新頭像
    void updateAvatar(String avatarUrl);


    //更新密码
    void updatePwd(String newPwd);
}
