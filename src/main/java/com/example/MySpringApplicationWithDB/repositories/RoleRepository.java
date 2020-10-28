package com.example.MySpringApplicationWithDB.repositories;

import com.example.MySpringApplicationWithDB.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
}
