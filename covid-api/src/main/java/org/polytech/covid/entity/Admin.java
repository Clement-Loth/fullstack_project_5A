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

public class Admin{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long admin_id;

    private String firstName;
    private String lastName;

	@ManyToOne	
    @JoinColumn(foreignKey=@ForeignKey(name="center_id"))
	private Center center;
    
}
