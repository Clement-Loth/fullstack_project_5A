package org.polytech.covid.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Doctors")

public class Doctor{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long doctor_id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name="center_id", nullable=false)
	private Center center;

    public Doctor(){

    }
       
    public Long getDoctor_id() {
        return doctor_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Center getCenter() {
        return center;
    }
    
    public void setCenter(Center center) {
        this.center = center;
    }
    
}