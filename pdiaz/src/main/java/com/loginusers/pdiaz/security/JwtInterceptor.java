package com.loginusers.pdiaz.security;

import com.loginusers.pdiaz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;


    @Autowired
    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.split(" ")[1].trim();

            if (jwtUtil.isTokenValid(token)) {
                String userId = jwtUtil.getUserIdFromToken(token);
                request.setAttribute("userId", userId);
                return true;
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }


}
