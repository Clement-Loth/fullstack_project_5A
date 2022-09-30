package org.polytech.covid.repository;

import java.util.List;

import org.polytech.covid.entity.Admin;
import org.polytech.covid.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    List<Admin> findByCenter(Center center);
    List<Admin> findByFirstName(String firstName);
    List<Admin> findByLastName(String lastName);

}
