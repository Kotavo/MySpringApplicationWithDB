package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.AuthenticationDto;
import com.example.MySpringApplicationWithDB.entities.Employee;
import com.example.MySpringApplicationWithDB.repositories.EmployeeRepository;
import com.example.MySpringApplicationWithDB.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final EmployeeRepository employeeRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, EmployeeRepository employeeRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDto requestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getMail(), requestDto.getPassword()));
            Employee employee = employeeRepository.findByMail(requestDto.getMail());
            if ( employee == null) {
                throw  new UsernameNotFoundException("User with mail " + requestDto.getMail() + " not found");
            }
            String token = jwtTokenProvider.createToken(requestDto.getMail(), employee.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("mail", requestDto.getMail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e) {
            throw  new BadCredentialsException("Invalid mail or password");
        }
    }
}
