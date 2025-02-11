package com.example.bigevent.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bigevent.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        String token = request.getHeader("token");
//        log.info("token: {}", token);
        if (token == null) {
            return false;
        }
        DecodedJWT decode = JWT.decode(token);
        String username = decode.getClaim("username").asString();
        Integer id = decode.getClaim("id").asInt();
        ThreadLocalUtil.set(id.toString());
        ThreadLocalUtil.set(username);
        request.setAttribute("username", username);
        request.setAttribute("id", id);
        return true;

    }
}
