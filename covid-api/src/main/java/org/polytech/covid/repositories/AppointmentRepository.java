package org.polytech.covid.repositories;

import java.util.Date;
import java.util.List;

import org.polytech.covid.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    List<Appointment> findAllByFirstName(String firstName);
    List<Appointment> findAllByLastName(String lastName);
    List<Appointment> findAllByDate(Date date);
}
