package org.polytech.covid.repository;

import org.polytech.covid.entity.SuperAdminJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdminJPA,Long>{
    
}
