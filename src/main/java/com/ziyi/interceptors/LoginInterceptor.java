package com.ziyi.interceptors;

import com.ziyi.utils.JwtUtil;
import com.ziyi.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author zhouYi
 * @description 校验Token
 * @date 2024/4/21 13:40
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    //校验Token
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> claim = JwtUtil.parseToken(token);
            //将用户信息存入ThreadLocal，防止其他参数重复传递与获取
            ThreadLocalUtil.set(claim);
            //没有报错表明解析正常，返回true，放行
            //校验token与redis中的token是否一致
            if (redisTemplate.opsForValue().get(token) == null) {
                throw new RuntimeException("token失效");
            }
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            //表示不放行
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //请求结束后清除ThreadLocal，防止内存泄露
        ThreadLocalUtil.remove();
    }
}



