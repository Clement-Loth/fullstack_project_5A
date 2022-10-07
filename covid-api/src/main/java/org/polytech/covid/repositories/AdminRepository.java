package org.polytech.covid.repositories;

import java.util.List;

import org.polytech.covid.entities.Admin;
import org.polytech.covid.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    List<Admin> findByCenter(Center center);
    List<Admin> findByFirstName(String firstName);
    List<Admin> findByLastName(String lastName);

}
