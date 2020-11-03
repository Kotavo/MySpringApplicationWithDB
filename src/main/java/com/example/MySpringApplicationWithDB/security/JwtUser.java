package com.example.MySpringApplicationWithDB.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Long id;

    private final String name;

    private final String surname;

    private final String position;

    private final String mail;

    private final String password;

    private final boolean deleted;

    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            Long id,
            String name,
            String surname,
            String position,
            String mail,
            String password,
            boolean deleted,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.position = position;
        this.password = password;
        this.deleted = deleted;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getSurname() {
        return surname;
    }

    @JsonIgnore
    public String getPosition() {
        return position;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
}
