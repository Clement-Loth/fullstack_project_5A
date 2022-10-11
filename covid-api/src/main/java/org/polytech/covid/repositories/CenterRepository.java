package org.polytech.covid.repositories;

import java.util.List;

import org.polytech.covid.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CenterRepository extends JpaRepository<Center, Long> {
    List<Center> findAllByName(String name);
}
