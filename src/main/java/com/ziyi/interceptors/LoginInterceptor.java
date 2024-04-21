package com.ziyi.interceptors;

import com.ziyi.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    //校验Token
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            JwtUtil.parseToken(token);
            //没有报错表明解析正常，返回true，放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            //表示不放行
            return false;
        }

    }
}
