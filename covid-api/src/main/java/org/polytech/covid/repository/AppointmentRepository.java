package org.polytech.covid.repository;

import org.polytech.covid.entity.AppointmentJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentJPA, Long>{
    
}
