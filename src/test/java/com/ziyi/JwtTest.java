package com.ziyi;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 13:14
 */
public class JwtTest {

    String SECRET = "ziyi";

    //测试生成JWT(三部分组成：头部声明算法、载荷存储信息、签名根据头部加密算法加密载荷)
    //载荷部分：不能存储敏感信息，因为载荷是根据base64编码的，其他人可以直接反编码拿到数据
    @Test
    public void testGen(){
        Map<String, Object> claim = new HashMap<>();
        claim.put("id","1");
        claim.put("username", "jack");
        String token = JWT.create().withClaim("user", claim)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(SECRET));
        System.out.println(token);

    }

    //测试解析token
    //解析失败情况：
    // 1. token过期
    // 2. token被篡改
    @Test
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoiamFjayJ9LCJleHAiOjE3MTM3MjA0NDl9" +
                ".QSmtcEqOiJ5Hq_t2EdWptijXlRXDhQupuqSVyteZ-Ic";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT info = jwtVerifier.verify(token);
        Claim user = info.getClaim("user");
        System.out.println(user);
    }
}
