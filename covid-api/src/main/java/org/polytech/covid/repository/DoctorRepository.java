package org.polytech.covid.repository;

import java.util.List;

import org.polytech.covid.entity.DoctorJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorJPA, Long> {
    List<DoctorJPA> findByName(String name);    
}
