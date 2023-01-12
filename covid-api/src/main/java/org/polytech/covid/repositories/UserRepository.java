package org.polytech.covid.repositories;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.entities.User;
import org.polytech.covid.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndDisabledFalse(String email);
    List<User> findAllByFirstNameAndDisabledFalse(String firstName);
    List<User> findAllByLastNameAndDisabledFalse(String lastName);
    List<User> findByRoleAndDisabledFalse(Role role);
    List<User> findDistinctByRoleAndCenter_IdAndDisabledFalse(Role role, Long center_id);
}