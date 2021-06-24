package com.patzzzcode.DanubeProject.jwt.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.patzzzcode.DanubeProject.jwt.model.JwtPrincipal;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class TokenResolver {

    private static final Object USER_ID = "userId";
    private final JwtExtractor jwtExtractor = new JwtExtractor();

    public Authentication resolve(String jwtToken) {

        Map<String, Object> claimsSet = jwtExtractor.getClaimSets(jwtToken);

        JwtPrincipal principal = new JwtPrincipal(jwtToken, claimsSet);

        List<String> roles = Optional.ofNullable(principal.getRoles()).orElse(null);
        List<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                .map(s -> new SimpleGrantedAuthority(s.toUpperCase())).collect(toList());

        Long userId = (Long) claimsSet.get(USER_ID);

        if (userId != null) {
            // e.g will add a role like: ROLE_ACCOUNT_STATUS_ACTIVE
            String userDetails = userId.toString();

            // accountRole = "{"+ "role"+:"+++accountRole + "," +"departemntId:" +
            // departmentId + ","+"hospitalId:" + hospitalId+"}";
            userDetails = "{ \"userId\":\"" + userDetails + "\"}";
            grantedAuthorities.add(new SimpleGrantedAuthority(userDetails));
        }

        return new UsernamePasswordAuthenticationToken(principal, null, grantedAuthorities);
    }
}
