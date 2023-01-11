package org.polytech.covid.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CENTERS")
public class Center{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    private String location;
    private String name;
    private String state;
    private String city;
    private boolean disabled;

    public Center(){
        
    }

    public Long getId() {
        return id;
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

    public boolean getDisabled (){
        return disabled;
    }

    public void setDisabled (boolean disabled){
        this.disabled = disabled;
    }
    
}


