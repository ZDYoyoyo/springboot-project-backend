package com.zdy.controller;

import com.zdy.pojo.Result;
import com.zdy.pojo.User;
import com.zdy.service.UserService;
import com.zdy.utils.JwtUtil;
import com.zdy.utils.Md5Util;
import com.zdy.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {

        // 查詢用戶
        User u = userService.findByUserName(username);
        if (u == null) {
            // 沒有佔用
            // 註冊
            userService.register(username, password);
            return Result.success();
        } else {
            // 佔用
            return Result.error("用戶名已被佔用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 根據用戶名查詢用戶
        User loginUser = userService.findByUserName(username);
        // 判斷該用戶是否存在
        if (loginUser == null) {
            return Result.error("用戶名錯誤");
        }

        // 判斷密碼是否正確  loginUser對象中的password是密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            // 登錄成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            // 把token存儲到redis中
            // 這裡把token作為key又作為value有一個bug，當在兩個不同瀏覽器同時登錄同一個賬號，它們登錄時使用的是不同的token，一方修改密碼不會影響另外一方的操作
//            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//            operations.set(token, token, 24, TimeUnit.DAYS);
            return Result.success(token);
        }
        return Result.error("密碼錯誤");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        // 根據用戶名查詢用戶
   /* Map<String, Object> map = JwtUtil.parseToken(token);
    String username = (String) map.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody  Map<String, String> params,@RequestHeader("Authorization") String token) {
        //1.校驗參數
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的參數");
        }

        //原密碼是否正確
        //調用userService根據用戶名拿到原密碼,再和old_pwd比對
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密碼填寫不正確");
        }

        //newPwd和rePwd是否一致
        if (!rePwd.equals(newPwd)){
            return Result.error("兩次填寫的新密碼不一致");
        }

        //2.調用service完成密碼更新
        userService.updatePwd(newPwd);
        //刪除redis中對應的token
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        operations.getOperations().delete(token);
        return Result.success();
    }


}
