package org.polytech.covid.entities;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CENTERS")
public class Center{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long center_id;

    private String location;
    private String name;
    private String state;
    private String city;


    @OneToMany(cascade={CascadeType.ALL}, targetEntity=Doctor.class, mappedBy="center")
    private List<Doctor> doctorList;

    @OneToMany(cascade={CascadeType.REMOVE}, targetEntity=Admin.class, mappedBy="center")
    private List<Admin> adminList;

    @OneToMany(cascade={CascadeType.REMOVE}, targetEntity=Appointment.class, mappedBy="center")
    private List<Appointment> appointmentList;

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

    @JsonIgnore
    public List<Doctor> getDoctorList() {
        return this.doctorList;
    }

    @JsonIgnore
    public List<Admin> getAdminList() {
        return this.adminList;
    }

    @JsonIgnore
    public List<Appointment> getAppointmentList() {
        return this.appointmentList;
    }
    
}


