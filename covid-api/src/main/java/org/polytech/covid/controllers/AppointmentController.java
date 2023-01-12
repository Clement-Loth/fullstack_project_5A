package org.polytech.covid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import org.polytech.covid.entities.Appointment;
import org.polytech.covid.repositories.AppointmentRepository;
import org.polytech.covid.entities.Center;

@RestController
@RequestMapping("/admin/app")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appRep;

    @GetMapping("")
    public List<Appointment> list(){
        return appRep.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getByid(@PathVariable long id){
        try{
            Appointment app = appRep.findById(id).orElseThrow();
            return new ResponseEntity<Appointment>(app, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/center/{center_id}")
    public ResponseEntity<List<Appointment>> getByCenter(@PathVariable Long center_id){
        List<Appointment> appList = appRep.findAllByCenter_idAndDisabledFalse(center_id);
        if(appList.size() <1){
            return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Appointment>>(appList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Appointment> newApp (@RequestParam String firstName, String lastName, Center center){
        Appointment newApp = new Appointment();
        newApp.setFirstName(firstName);
        newApp.setLastName(lastName);
        newApp.setCenter(center);
        appRep.save(newApp);
        return new ResponseEntity<Appointment>(newApp, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateApp(@RequestParam String firstName, String lastName, Center center, @PathVariable long id){
        Appointment app = appRep.findById(id).orElseThrow();
        app.setFirstName(firstName);
        app.setLastName(lastName);
        app.setCenter(center);
        appRep.save(app);
        return new ResponseEntity<Appointment>(app,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> deleteApp(@PathVariable long id){
        Appointment app = appRep.findById(id).orElseThrow();
        app.setDisabled(true);
        appRep.save(app);
        return new ResponseEntity<Appointment>(app, HttpStatus.OK);
    }
}