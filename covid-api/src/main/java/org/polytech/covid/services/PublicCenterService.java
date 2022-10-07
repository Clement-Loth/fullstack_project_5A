package org.polytech.covid.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.polytech.covid.entities.Center;
import org.polytech.covid.repositories.CenterRepository;

@Service
public class PublicCenterService {
    @Autowired
    private CenterRepository centerRep;
    
    public List<Center> ListCenterAll(){
        return centerRep.findAll();
    }

    public List<Center> ListCenterbyCity(String city){
        List<Center> centers =  centerRep.findByCity(city);
        if(centers.size() <1)
        throw new NoSuchElementException("No center in this city!");
        return centers;
    }

    public List<Center> GetCenterByName(String name){
        return centerRep.findByName(name);
    }


}
