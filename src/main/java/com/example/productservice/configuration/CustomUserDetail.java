package com.example.productservice.configuration;

import com.example.productservice.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter
public class CustomUserDetail implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Boolean status;
    List<GrantedAuthority> roleList;

    public CustomUserDetail(UserEntity user) {
        this.username = user.getLogin();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.roleList = List.of(new SimpleGrantedAuthority(user.getUserRole().getName()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList;
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
        return status;
    }
}
