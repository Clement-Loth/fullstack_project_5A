package org.polytech.covid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Appointment{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long appointment_id;

    private String firstName;
    private String lastName;
    private java.util.Date date;
    private String phone;
    private String email;
    private boolean validated;


	@ManyToOne	
    @JoinColumn(foreignKey=@ForeignKey(name="center_id"))
	private Center center;
    
}