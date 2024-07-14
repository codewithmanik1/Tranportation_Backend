package com.om.operations.security.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.om.operations.masters.entity.Employee;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@ToString
public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private Boolean isActive;

    private GrantedAuthority authority;

    public UserDetailsImpl(Long id, String username, String password, Boolean isActive,
                           GrantedAuthority authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.isActive = isActive;
    }

    public static UserDetailsImpl build(Employee user) {
        //right now no role to user so set admin
        String role = "Admin";//userRepository.getUserTypeByUsername(user.getUserName());
        // if (user!=null)
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);

        return new UserDetailsImpl(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getIsActive(),
                grantedAuthority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive;
    }
}