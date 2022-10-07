package org.polytech.covid.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.polytech.covid.entities.Appointment;
import org.polytech.covid.repositories.AppointmentRepository;

@Service
public class AdminAppointmentService {
    @Autowired
    private AppointmentRepository appRep;

    public List<Appointment> ListAppointmentAll(){
        return appRep.findAll();
    }

    public Appointment getAppointment (long id){
        return appRep.findById(id).get();
    }
    
    public List<Appointment> ListAppointmentByFirstName(String firstName){
        List<Appointment> apps = appRep.findByFirstName(firstName);
        if(apps.size() <1){
            throw new NoSuchElementException("No clients with that first name!");
        }
        return apps;
    }

    public List<Appointment> ListAppointmentByLastName (String lastName){
        List<Appointment> apps = appRep.findByLastName(lastName);
        if(apps.size() <1){
            throw new NoSuchElementException("No clients with that last name!");
        }
        return apps;
    }

    public List<Appointment> ListAppointmentByDate (Date date){
        List<Appointment> apps = appRep.findByDate(date);
        if(apps.size() <1){
            throw new NoSuchElementException("No appointments at that date!");
        }
        return apps;
    }

    public void updateAppointmentValidated (Appointment app, boolean validated){
        app.setValidated(validated);
        appRep.save(app);
    }
}
