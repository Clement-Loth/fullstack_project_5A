package org.polytech.covid.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.polytech.covid.entities.Doctor;
import org.polytech.covid.repositories.CenterRepository;
import org.polytech.covid.repositories.DoctorRepository;
import org.polytech.covid.entities.Center;

@Service
public class AdminDoctorService {
    @Autowired
    private DoctorRepository doctorRep;
    @Autowired
    private CenterRepository centerRep;

    public List<Doctor> ListDoctorAll(){
        return doctorRep.findAll();
    }

    public List<Doctor> ListDoctorByCenter(Center center){
        List<Doctor> Doctors = doctorRep.findByCenter(center);
        if(Doctors.size() <1){
            throw new NoSuchElementException("No Doctors in this center!");
        }
        return Doctors;
    }

    public List<Doctor> ListDoctorByCenter(String centerName){
        List<Doctor> Doctors = doctorRep.findByCenter(centerName);
        if(Doctors.size() <1){
            throw new NoSuchElementException("No Doctors in this center!");
        }
        return Doctors;
    }

    public List<Doctor> ListDoctorByFirstName(String firstName){
        List<Doctor> Doctors = doctorRep.findByFirstName(firstName);
        if(Doctors.size() <1){
            throw new NoSuchElementException("No Doctor with this first name!");
        }
        return Doctors;
    }

    public List<Doctor> ListDoctorByLastName(String lastName){
        List<Doctor> Doctors = doctorRep.findByLastName(lastName);
        if(Doctors.size() <1){
            throw new NoSuchElementException("No Doctor with this last name!");
        }
        return Doctors;
    }

    public void saveDoctor(String firstName, String lastName, Center center){
        Doctor Doctor = new Doctor();
        Doctor.setFirstName(firstName);
        Doctor.setLastName(lastName);
        Doctor.setCenter(center);
        doctorRep.save(Doctor);
        List<Doctor> DoctorList= center.getDoctorList();
        DoctorList.add(Doctor);
        center.setDoctorList(DoctorList);
        centerRep.save(center);
    }

    public void updateDoctorFirstName (Doctor Doctor, String firstName){
        Doctor.setFirstName(firstName);
        doctorRep.save(Doctor);
    }

    public void updateDoctorLastName (Doctor Doctor, String lastName){
        Doctor.setLastName(lastName);
        doctorRep.save(Doctor);
    }

    public void updateDoctorCenter (Doctor Doctor, Center center){
        Center oldCenter = Doctor.getCenter();
        List<Doctor> oldCenterListDoctor = oldCenter.getDoctorList();
        oldCenterListDoctor.remove(Doctor);
        centerRep.save(oldCenter);
        Doctor.setCenter(center);
        doctorRep.save(Doctor);
        List<Doctor> newCenterListDoctor = center.getDoctorList();
        newCenterListDoctor.add(Doctor);
        center.setDoctorList(newCenterListDoctor);
        centerRep.save(center);
    }
}