package com.zdy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest// 如果在測試類上添加了這個註解，那麼將來單元測試方法執行之前，會先初始化Spring容器
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
//    @Test
    public void testSet(){
        // 往redis中存儲一個鍵值對  StringRedisTemplate
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

        operations.set("username","zhangsan");
        operations.set("id","1",15, TimeUnit.SECONDS);
    }

//    @Test
    public void testGet(){
        // 從redis中獲取一個鍵值對
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        System.out.println(operations.get("username"));
        System.out.println(operations.get("id"));
    }
}
