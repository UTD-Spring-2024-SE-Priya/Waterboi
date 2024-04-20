package com.Waterboi.API.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AppuserDetails implements UserDetails {

    private final Appuser appuser;
    public AppuserDetails(Appuser appuser) {
        this.appuser = appuser;
    }

    public Long getUserId() {
        return appuser.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return appuser.getPassword();
    }

    @Override
    public String getUsername() {
        return appuser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
