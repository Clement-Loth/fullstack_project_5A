org.polytech.covid.entity;

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

    public Appointment (){
        
    }

    public Long getAppointment_id() {
        return appointment_id;
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


    public boolean isValidated() {
        return validated;
    }


    public void setValidated(boolean validated) {
        this.validated = validated;
    }


    public Center getCenter() {
        return center;
    }


    public void setCenter(Center center) {
        this.center = center;
    }
    
}