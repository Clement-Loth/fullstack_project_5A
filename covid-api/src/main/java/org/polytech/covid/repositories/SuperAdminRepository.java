package org.polytech.covid.repositories;

import java.util.List;

import org.polytech.covid.entities.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long>{
    //List<SuperAdmin> findByName(String name);
}