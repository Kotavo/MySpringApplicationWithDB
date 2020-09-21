package com.example.MySpringApplicationWithDB.repository;

import com.example.MySpringApplicationWithDB.enity.WorkProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkProcessRepository extends JpaRepository<WorkProcess,Long> {
}
