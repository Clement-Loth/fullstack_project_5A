package db.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import db.repositories.CenterRepository;
import db.Entity.Center;
import db.repositories.AppointmentRepository;
import db.Entity.Appointment;

@Service
public class PublicAppointmentService {

    @Autowired 
    private AppointmentRepository apprep;

    @Autowired
    private CenterRepository centerrep;

    
    public void saveAppointment(String firstname, String lastname, String email, String phone, Center center){
        Appointment appointment = new Appointment();
        appointment.setFirstName(firstname);
        appointment.setLastName(lastname);
        appointment.setEmail(email);
        appointment.setPhone(phone);
        Date date = new Date(System.currentTimeMillis());
        appointment.setDate(date);
        appointment.setCenter(center);
        apprep.save(appointment);
        List<Appointment> applist = center.getAppointmentList();
        applist.add(appointment);
        center.setAppointmentList(applist);
        centerrep.save(center);
    }
    
}
