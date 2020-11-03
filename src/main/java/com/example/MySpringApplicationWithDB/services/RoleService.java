package com.example.MySpringApplicationWithDB.services;

import com.example.MySpringApplicationWithDB.entities.Role;
import com.example.MySpringApplicationWithDB.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public void delete(String name) {
        roleRepository.deleteById(name);
    }
}
