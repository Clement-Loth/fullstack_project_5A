package org.polytech.covid.repository;

import org.polytech.covid.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
