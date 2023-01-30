package com.woniuxy.qiantai.interceptors;

import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.qiantai.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        try{
            JwtUtils.getAccount(userTokenFromCookie);
        }catch (Exception e){
            request.getRequestDispatcher("/user/redirectLoginHtml").forward(request,response);
            return false;
        }

        return true;
    }
}
