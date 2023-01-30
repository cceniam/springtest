package com.woniuxy.qiantai.interceptors;

import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.qiantai.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //做token续期操作

        //获取到cookie中的token
        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        try {
            if(!StringUtils.isEmpty(userTokenFromCookie)){
                String account = JwtUtils.getAccount(userTokenFromCookie);
            }
        }catch (ExpiredJwtException expiredJwtException){
            //捕获到过期异常,需要做token续期
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

            String redisAccount = operations.get(userTokenFromCookie);
            if (!StringUtils.isEmpty(redisAccount)) {
                //续期  更新cookie中的token
                String refreshToken = JwtUtils.createToken(redisAccount, 15);
                CookieUtils.setUserToken2Cookie(response, refreshToken);

                //续期 同步更新redis中数据
                stringRedisTemplate.delete(userTokenFromCookie);
                operations.set(refreshToken, redisAccount, 30, TimeUnit.MINUTES);

                System.out.println("token续期 "+refreshToken);
            }

        }

        return true;
    }
}
