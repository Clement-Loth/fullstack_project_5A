package org.polytech.covid.repository;

import java.util.List;

import org.polytech.covid.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Long> {
    List<Center> findByName(String name);
    List<Center> findByCity(String city);
}