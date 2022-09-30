package org.polytech.covid.repository;

import java.util.List;

import org.polytech.covid.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long>{
    List<SuperAdmin> findByName(String name);
}
