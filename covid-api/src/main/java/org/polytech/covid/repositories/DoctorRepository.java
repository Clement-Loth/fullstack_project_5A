package org.polytech.covid.repositories;

import java.util.List;

import org.polytech.covid.entities.Center;
import org.polytech.covid.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // List<Doctor> findByFirstName(String firstName);
    // List<Doctor> findByLastName(String lastName);    
    // List<Doctor> findByCenter(Center center);
    // List<Doctor> findByCenter(String centerName);
}