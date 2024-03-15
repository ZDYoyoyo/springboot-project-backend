package com.zdy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
//
//    @Test
    public void testGen() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", 1);
    claims.put("username", "柚子");
    // 生成jwt的程式碼
    String token = JWT.create()
            .withClaim("user", claims)// 添加載荷
            .withExpiresAt(new Date(System.currentTimeMillis() + 100000))// 添加過期時間
            .sign(Algorithm.HMAC256("itheima"));// 指定演算法，配置金鑰

    System.out.println(token);
}

//    @Test
    public void testParse() {
        // 定義字串，模擬使用者傳遞過來的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuafmuWtkCJ9LCJleHAiOjE3MDgyNDY2MTJ9.qHuEf1jEjrHhuen9CsSZ1BSMwBa_Tl-4orGYRyyCLmc";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);// 驗證token，生成一個解析後的JWT物件
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        // 如果篡改了標頭和載荷部分的資料，那麼驗證失敗
        // 如果金鑰改了，驗證失敗
        // token過期
    }
}

