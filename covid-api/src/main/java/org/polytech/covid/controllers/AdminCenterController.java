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

import org.polytech.covid.entities.Center;
import org.polytech.covid.services.AdminCenterService;

@RestController
@RequestMapping("/admin/center")
public class AdminCenterController {
    @Autowired
    AdminCenterService adminCenterService;

    @GetMapping("")
    public List<Center> list(){
        return adminCenterService.ListCenterAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Center> getByid(@PathVariable long id){
        try{
            Center center = adminCenterService.getCenter(id);
            return new ResponseEntity<Center>(center, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Center>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("town/{city}")
    public ResponseEntity<List<Center>> getByCity(@PathVariable String city){
        try{
            List<Center> centerList = adminCenterService.ListCenterByCity(city);
            return new ResponseEntity<List<Center>>(centerList, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<List<Center>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Center>> getByName(@PathVariable String name){
        try{
            List<Center> centerList = adminCenterService.ListCenterByName(name);
            return new ResponseEntity<List<Center>>(centerList, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<List<Center>>(HttpStatus.NOT_FOUND);
        }
    }
}
