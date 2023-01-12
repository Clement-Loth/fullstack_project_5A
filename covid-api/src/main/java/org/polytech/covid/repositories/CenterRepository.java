package org.polytech.covid.repositories;

import java.util.List;

import org.polytech.covid.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Long> {
    List<Center> findAllByName(String name);
    List<Center> findAllByCity(String city);
}
