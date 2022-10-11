package org.polytech.covid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.polytech.covid.entities.Center;
import org.polytech.covid.repositories.CenterRepository;
import org.polytech.covid.services.AdminCenterService;

@RestController
@RequestMapping("/admin/center")
public class AdminCenterController {
    @Autowired
    CenterRepository centerRepository;

    @GetMapping("")
    public List<Center> list(){
        return centerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Center> getById(@PathVariable long id){
        return centerRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<Center> listByName(@PathVariable String name){
        return centerRepository.findAllByName(name);
    }
}
