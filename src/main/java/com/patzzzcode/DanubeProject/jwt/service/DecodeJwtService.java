package com.patzzzcode.DanubeProject.jwt.service;

import com.google.gson.Gson;
import com.patzzzcode.DanubeProject.jwt.config.JwtTokenUtil;
import com.patzzzcode.DanubeProject.jwt.model.JwtAuthorities;
import com.patzzzcode.DanubeProject.jwt.model.JwtPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class DecodeJwtService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthorities decodeJWT(UsernamePasswordAuthenticationToken principal) {
        JwtPrincipal jwtPrincipal = (JwtPrincipal) principal.getPrincipal();
        String json = principal.getAuthorities().toString().replace('[', ' ');
        json = json.replace(']', ' ');
        Gson g = new Gson();
        String username = jwtTokenUtil.getAllClaimsFromToken(jwtPrincipal.getJwtToken()).get("sub").toString();
        JwtAuthorities s = g.fromJson(json, JwtAuthorities.class);
        s.setUsername(username);

        return s;
    }
}
