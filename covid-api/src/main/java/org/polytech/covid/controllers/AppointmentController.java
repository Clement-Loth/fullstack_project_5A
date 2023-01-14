package org.polytech.covid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.polytech.covid.entities.Appointment;
import org.polytech.covid.repositories.AppointmentRepository;
import org.polytech.covid.entities.User;
import org.polytech.covid.repositories.UserRepository;
import org.polytech.covid.entities.Center;
import org.polytech.covid.repositories.CenterRepository;
import org.polytech.covid.entities.Role;

@RestController
@RequestMapping("")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appRep;
    @Autowired
    private UserRepository userRep;
    @Autowired
    private CenterRepository centerRep;

    @RolesAllowed("SuperAdministrator")
    @GetMapping("/admin/app")
    public List<Appointment> list(){
        return appRep.findAll();
    }

    @GetMapping("/admin/app/doctor/{doctor_id}")
    public ResponseEntity<List<Appointment>> getByDoctor(@PathVariable Long doctorId){
        List<Appointment> appList = appRep.findAllByDoctor_id(doctorId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRep.findByEmail(authentication.getName());
        if(appList.size()<1 || !user.isPresent() || user.get().getRole() != Role.SuperAdministrator && !doctorId.equals(user.get().getId())) 
            return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Appointment>>(appList, HttpStatus.OK);
    }

    @GetMapping("/admin/app/{id}")
    public ResponseEntity<Appointment> getByid(@PathVariable long id){
        try{
            Appointment app = appRep.findById(id).orElseThrow();
            return new ResponseEntity<Appointment>(app, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/admin/app/center/{center_id}")
    public ResponseEntity<List<Appointment>> getByCenter(@PathVariable Long centerId){
        List<Appointment> appList = appRep.findAllByCenter_id(centerId);
        if(appList.size() <1){
            return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Appointment>>(appList, HttpStatus.OK);
    }

    @PostMapping("/public/app/")
    public ResponseEntity<Appointment> newApp (@RequestParam String firstName, String lastName, Long centerId){
        Appointment newApp = new Appointment();
        newApp.setFirstName(firstName);
        newApp.setLastName(lastName);
        Optional <Center> center = centerRep.findById(centerId);
        if (!center.isPresent())
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        newApp.setCenter(center.get());
        appRep.save(newApp);
        return new ResponseEntity<Appointment>(newApp, HttpStatus.OK);
    }

    @RolesAllowed({"SuperAdministrator","Doctor"})
    @PutMapping("/admin/app/{id}")
    public ResponseEntity<Appointment> updateApp(@RequestParam String firstName, String lastName, Long centerId, @PathVariable long id){
        Optional <Appointment> app = appRep.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRep.findByEmail(authentication.getName());
        Optional<Center> center = centerRep.findById(centerId);
        if (!center.isPresent() || !app.isPresent() || !user.isPresent() || (user.get().getRole() != Role.SuperAdministrator && user.get() != app.get().getDoctor()))
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        app.get().setFirstName(firstName);
        app.get().setLastName(lastName);
        app.get().setCenter(center.get());
        appRep.save(app.get());
        return new ResponseEntity<Appointment>(app.get(),HttpStatus.OK);
    }

    @RolesAllowed({"SuperAdministrator","Doctor"})
    @DeleteMapping("/admin/app/{id}")
    public ResponseEntity<Appointment> deleteApp(@PathVariable long id){
        Optional <Appointment> app = appRep.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRep.findByEmail(authentication.getName());
        if (!app.isPresent() || !user.isPresent() || (user.get().getRole() != Role.SuperAdministrator && user.get() != app.get().getDoctor()))
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        app.get().setDisabled(true);
        appRep.save(app.get());
        return new ResponseEntity<Appointment>(app.get(), HttpStatus.OK);
    }
}