package org.polytech.covid.repository;

import java.util.List;

import org.polytech.covid.entity.CenterJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<CenterJPA, Long> {
    List<CenterJPA> findByName(String name);
}