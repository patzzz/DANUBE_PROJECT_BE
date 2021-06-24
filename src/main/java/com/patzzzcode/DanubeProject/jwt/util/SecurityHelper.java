package com.patzzzcode.DanubeProject.jwt.util;

import com.patzzzcode.DanubeProject.jwt.model.JwtPrincipal;
import com.patzzzcode.DanubeProject.jwt.model.TokenResolver;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;


public final class SecurityHelper {

    private SecurityHelper() {
    }

    public static JwtPrincipal getPrincipal() {
        return (JwtPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void setAuthenticationUsingJwt(String jwtToken) {
        Authentication authentication = SpringContextHelper.getBean(TokenResolver.class).resolve(jwtToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
