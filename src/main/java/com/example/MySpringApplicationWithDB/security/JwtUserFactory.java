package com.example.MySpringApplicationWithDB.security;

import com.example.MySpringApplicationWithDB.entities.Employee;
import com.example.MySpringApplicationWithDB.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(Employee employee){
        return  new JwtUser(
                employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getPosition(),
                employee.getMail(),
                employee.getPassword(),
                employee.isDeleted(),
                mapToGrantedAuthorities(employee.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
