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
import org.polytech.covid.entities.Center;
import org.polytech.covid.repositories.CenterRepository;

@RestController
@RequestMapping("/admin/center")
public class AdminCenterController {
    @Autowired
    private CenterRepository centerRep;

    @GetMapping("")
    public List<Center> list(){
        return centerRep.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Center> getByid(@PathVariable long id){
        try{
            Center center = centerRep.findById(id).orElseThrow();
            return new ResponseEntity<Center>(center, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Center>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Center>> getByCity(@PathVariable String city){
        List<Center> centerList = centerRep.findAllByCity(city);
        if(centerList.size() <1){
            return new ResponseEntity<List<Center>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Center>>(centerList, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Center>> getByName(@PathVariable String name){
        List<Center> centerList = centerRep.findAllByName(name);
        if(centerList.size() <1){
            return new ResponseEntity<List<Center>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Center>>(centerList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Center> newCenter(@RequestParam String centerName, String centerCity, String centerState, String centerLocation){
        Center newCenter = new Center();
        newCenter.setCity(centerCity);
        newCenter.setLocation(centerLocation);
        newCenter.setName(centerName);
        newCenter.setState(centerState);
        centerRep.save(newCenter);
        return new ResponseEntity<Center>(newCenter,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Center> updateCenter(@RequestParam String centerName, String centerCity, String centerState, String centerLocation, @PathVariable long id){
        Center center = centerRep.findById(id).orElseThrow();
        center.setCity(centerCity);
        center.setLocation(centerLocation);
        center.setName(centerName);
        center.setState(centerState);
        centerRep.save(center);
        return new ResponseEntity<Center>(center,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Center> deleteCenter(@PathVariable long id){
        Center center = centerRep.findById(id).orElseThrow();
        center.setDisabled(true);
        centerRep.save(center);
        return new ResponseEntity<Center>(center, HttpStatus.OK);
    }
}
