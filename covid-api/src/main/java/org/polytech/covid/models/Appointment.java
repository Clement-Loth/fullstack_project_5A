package org.polytech.covidapi.models;

import javax.persistence.Entity;

@Entity
public class Appointment {
    private int id;
    private String mail;
    private String phone;
    private String lastname;
    private String name;
    private java.util.Date date;
    private boolean validated;
}
