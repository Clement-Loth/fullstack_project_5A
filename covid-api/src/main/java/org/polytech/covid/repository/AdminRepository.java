package org.polytech.covid.repository;

import org.polytech.covid.entity.AdminJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminJPA,Long>{
    
}
