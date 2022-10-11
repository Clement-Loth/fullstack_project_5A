package org.polytech.covid.repositories;

import java.util.List;

import org.polytech.covid.entities.Center;
import org.polytech.covid.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByFirstName(String firstName);
    List<Doctor> findAllByLastName(String lastName);    
    List<Doctor> findAllByCenter(Center center);
    List<Doctor> findAllByCenter(String centerName);
}