package com.ch.booklib.interceptors;


import com.ch.booklib.utils.CookieUtils;
import com.ch.booklib.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



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
