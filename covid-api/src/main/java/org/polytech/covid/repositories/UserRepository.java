package org.polytech.covid.repositories;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.entities.User;
import org.polytech.covid.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByFirstName(String firstName);
    List<User> findAllByLastName(String lastName);
    List<User> findAllByCenterId(Long centerId);
    List<User> findByRole(Role role);
    List<User> findDistinctByRoleAndCenterId(Role role, Long centerId);
}