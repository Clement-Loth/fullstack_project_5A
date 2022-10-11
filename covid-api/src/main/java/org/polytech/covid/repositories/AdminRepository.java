package org.polytech.covid.repositories;

import java.util.List;

import org.polytech.covid.entities.Admin;
import org.polytech.covid.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    List<Admin> findAllByCenter(Center center);
    List<Admin> findAllByCenter(String centerName);
    List<Admin> findAllByFirstName(String firstName);
    List<Admin> findAllByLastName(String lastName);
}