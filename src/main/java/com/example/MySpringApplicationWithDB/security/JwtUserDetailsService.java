package com.example.MySpringApplicationWithDB.security;

import com.example.MySpringApplicationWithDB.entities.Employee;
import com.example.MySpringApplicationWithDB.repositories.EmployeeRepository;
import com.example.MySpringApplicationWithDB.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public JwtUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByMail(mail);
        if(employee == null) {
            throw new UsernameNotFoundException("User with email: "+ mail + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(employee);
        return jwtUser;
    }
}
