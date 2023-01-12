package org.polytech.covid.repositories;

import java.util.Date;
import java.util.List;

import org.polytech.covid.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    List<Appointment> findAllByFirstNameAndDisabledFalse(String firstName);
    List<Appointment> findAllByLastNameAndDisabledFalse(String lastName);
    List<Appointment> findAllByDateAndDisabledFalse(Date date);
    List<Appointment> findAllByCenter_idAndDisabledFalse(Long center_id);
}
