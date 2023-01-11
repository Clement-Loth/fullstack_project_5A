package org.polytech.covid.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Appointments")

public class Appointment{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    private String firstName;
    private String lastName;
    private java.util.Date date;
    private String phone;
    private String email;
    private boolean disabled;
    private String state;


	@ManyToOne	
    @JoinColumn(foreignKey=@ForeignKey(name="center_id"))
	private Center center;

    // @ManyToOne	
    // @JoinColumn(foreignKey=@ForeignKey(name="user_id"))
	// private Long doctor;

    public Appointment (){
        
    }

    public Long getId() {
        return id;
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


    public java.util.Date getDate() {
        return date;
    }


    public void setDate(java.util.Date date) {
        this.date = date;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state = state;
    }

    public Center getCenter() {
        return center;
    }


    public void setCenter(Center center) {
        this.center = center;
    }


    // public User getUser() {
    //     return doctor;
    // }


    // public void setUser(User user) {
    //     this.doctor = user;
    // }

    public boolean getDisabled (){
        return disabled;
    }

    public void setDisabled (boolean disabled){
        this.disabled = disabled;
    }
    
}