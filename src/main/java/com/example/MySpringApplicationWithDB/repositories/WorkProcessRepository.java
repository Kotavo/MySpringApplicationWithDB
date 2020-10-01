package com.example.MySpringApplicationWithDB.repositories;

import com.example.MySpringApplicationWithDB.entities.WorkProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkProcessRepository extends JpaRepository<WorkProcess, Long> {
}
