package org.polytech.covid.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity

public class Center{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long center_id;

    private String location;
    private String name;


    @OneToMany(cascade={CascadeType.REMOVE}, targetEntity=Doctor.class, mappedBy="center")
    private List<Doctor> DoctorList;

    @OneToMany(cascade={CascadeType.REMOVE}, targetEntity=Admin.class, mappedBy="center")
    private List<Admin> AdminList;

    @OneToMany(cascade={CascadeType.REMOVE}, targetEntity=Appointment.class, mappedBy="center")
    private List<Appointment> AppointmentList;

    public Center(){
        
    }

    public Long getCenter_id() {
        return center_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctor> getDoctorList() {
        return DoctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        DoctorList = doctorList;
    }

    public List<Admin> getAdminList() {
        return AdminList;
    }

    public void setAdminList(List<Admin> adminList) {
        AdminList = adminList;
    }

    public List<Appointment> getAppointmentList() {
        return AppointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        AppointmentList = appointmentList;
    }
    
}


