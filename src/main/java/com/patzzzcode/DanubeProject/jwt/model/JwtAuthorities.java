package com.patzzzcode.DanubeProject.jwt.model;

public class JwtAuthorities {
    private String username;
    private Long userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
