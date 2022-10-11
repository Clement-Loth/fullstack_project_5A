package org.polytech.covid.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="CENTERS")
public class Center{

    @Autowired
    private EntityManager entityManager;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long center_id;

    private String location;
    private String name;
    private String state;
    private String city;


    //@OneToMany(cascade={CascadeType.ALL}, targetEntity=Doctor.class, mappedBy="center")
    //@OneToMany(cascade={CascadeType.ALL}, orphanRemoval = true, mappedBy="center")
    //private List<Doctor> DoctorList;

    // @OneToMany(cascade={CascadeType.REMOVE}, targetEntity=Admin.class, mappedBy="center")
    // private List<Admin> AdminList;

    // @OneToMany(cascade={CascadeType.REMOVE}, targetEntity=Appointment.class, mappedBy="center")
    // private List<Appointment> AppointmentList;

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

    public String getState (){
        return state;
    }

    public void setState (String state){
        this.state = state;
    }

    public String getCity (){
        return this.city;
    }

    public void setCity (String city){
        this.city = city;
    }

    // public List<Doctor> getDoctorList() {
    //     entityManager = Persistence.createEntityManagerFactory(null).createEntityManager();
    //     return entityManager
    //          .createQuery("select d from doctors d where d.center_id = :center_id", Doctor.class)
    //          .setParameter("center_id", center_id)
    //          .getResultList();
    // }

    // public void setDoctorList(List<Doctor> doctorList) {
    //     DoctorList = doctorList;
    // }

    // public List<Admin> getAdminList() {
    //     return AdminList;
    // }

    // public void setAdminList(List<Admin> adminList) {
    //     AdminList = adminList;
    // }

    // public List<Appointment> getAppointmentList() {
    //     return AppointmentList;
    // }

    // public void setAppointmentList(List<Appointment> appointmentList) {
    //     AppointmentList = appointmentList;
    // }
    
}


