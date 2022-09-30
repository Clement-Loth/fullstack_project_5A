package org.polytech.covid.repository;

import java.util.List;

import org.polytech.covid.entity.Center;
import org.polytech.covid.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByFirstName(String firstName);
    List<Doctor> findByLastName(String lastName);    
    List<Doctor> findByCenter(Center center);
}
