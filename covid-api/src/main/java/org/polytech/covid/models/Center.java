package org.polytech.covid.models;

import javax.persistence.Entity;

@Entity
public class Center {
    private int id;
    private String city;
    private String name;
    private String location;

}