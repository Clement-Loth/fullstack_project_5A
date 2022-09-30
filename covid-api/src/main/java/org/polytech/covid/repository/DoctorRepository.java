package org.polytech.covid.repository;

import org.polytech.covid.entity.DoctorJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorJPA, Long> {
    
}
