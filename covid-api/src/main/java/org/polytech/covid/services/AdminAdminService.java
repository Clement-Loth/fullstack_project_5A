package org.polytech.covid.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.polytech.covid.entities.Admin;
import org.polytech.covid.entities.Center;
import org.polytech.covid.repositories.AdminRepository;
import org.polytech.covid.repositories.CenterRepository;

@Service
public class AdminAdminService {
    @Autowired
    private AdminRepository adminRep;
    @Autowired
    private CenterRepository centerRep;

    public List<Admin> ListAdminAll(){
        return adminRep.findAll();
    }

    public List<Admin> ListAdminByCenter(Center center){
        List<Admin> admins = adminRep.findByCenter(center);
        if(admins.size() <1){
            throw new NoSuchElementException("No admins in this center!");
        }
        return admins;
    }

    public List<Admin> ListAdminByCenter(String centerName){
        List<Admin> admins = adminRep.findByCenter(centerName);
        if(admins.size() <1){
            throw new NoSuchElementException("No admins in this center!");
        }
        return admins;
    }

    public List<Admin> ListAdminByFirstName(String firstName){
        List<Admin> admins = adminRep.findByFirstName(firstName);
        if(admins.size() <1){
            throw new NoSuchElementException("No admin with this first name!");
        }
        return admins;
    }

    public List<Admin> ListAdminByLastName(String lastName){
        List<Admin> admins = adminRep.findByLastName(lastName);
        if(admins.size() <1){
            throw new NoSuchElementException("No admin with this last name!");
        }
        return admins;
    }

    public void saveAdmin(String firstName, String lastName, Center center){
        Admin admin = new Admin();
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setCenter(center);
        adminRep.save(admin);
        List<Admin> adminList= center.getAdminList();
        adminList.add(admin);
        center.setAdminList(adminList);
        centerRep.save(center);
    }

    public void updateAdminFirstName (Admin admin, String firstName){
        admin.setFirstName(firstName);
        adminRep.save(admin);
    }

    public void updateAdminLastName (Admin admin, String lastName){
        admin.setLastName(lastName);
        adminRep.save(admin);
    }

    public void updateAdminCenter (Admin admin, Center center){
        Center oldCenter = admin.getCenter();
        List<Admin> oldCenterListAdmin = oldCenter.getAdminList();
        oldCenterListAdmin.remove(admin);
        centerRep.save(oldCenter);
        admin.setCenter(center);
        adminRep.save(admin);
        List<Admin> newCenterListAdmin = center.getAdminList();
        newCenterListAdmin.add(admin);
        center.setAdminList(newCenterListAdmin);
        centerRep.save(center);
    }
}