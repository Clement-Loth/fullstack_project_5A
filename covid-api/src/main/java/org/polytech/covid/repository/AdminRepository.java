package org.polytech.covid.repository;

import java.util.List;

import org.polytech.covid.entity.AdminJPA;
import org.polytech.covid.entity.CenterJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminJPA,Long>{
    List<AdminJPA> findByCenter(CenterJPA center);
    List<AdminJPA> findByName(String name);
}
