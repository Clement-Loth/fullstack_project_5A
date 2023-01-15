package org.polytech.covid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.polytech.covid.entities.User;
import org.polytech.covid.entities.Role;
import org.polytech.covid.repositories.UserRepository;
import org.polytech.covid.entities.Center;
import org.polytech.covid.repositories.CenterRepository;
import org.polytech.covid.services.UserService;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserRepository userRep;
    @Autowired
    private CenterRepository centerRep;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RolesAllowed("SuperAdministrator")
    @GetMapping("/admin/user/")
    public List<User> list(){
        return userRep.findAll();
    }


    @GetMapping("/admin/user/{id}")
    public ResponseEntity<User> getByid(@PathVariable long id){
        Optional <User> target = userRep.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRep.findByEmail(authentication.getName());
        if(!user.isPresent() || (user.get().getRole() != Role.SuperAdministrator && id != user.get().getId())) 
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);    
        return new ResponseEntity<User>(target.get(), HttpStatus.OK);
    }

    @GetMapping("/admin/user/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email){
        Optional <User> target = userRep.findByEmail(email);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRep.findByEmail(authentication.getName());
        if(!user.isPresent() || user.get().getRole() != Role.SuperAdministrator && !email.equals(user.get().getEmail())) 
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<User>(target.get(), HttpStatus.OK);
    }

    @GetMapping("/public/user/doctor/{centerId}")
    public ResponseEntity<List<User>> getDoctor(@PathVariable Long centerId){
        List<User> userList = userRep.findAllByCenterId(centerId);
        List<User> doctorList = new ArrayList<User>();
        for (User user : userList) {
            if (user.getRole()==Role.Doctor)
                doctorList.add(user);
        }
        if(doctorList.size() <1){
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(doctorList,HttpStatus.OK);
    }

    @GetMapping("/admin/user/role/{role}")
    public ResponseEntity<List<User>> getByRole(@PathVariable String role){
        try {
            List<User> userList = userRep.findByRole(Role.valueOf(role));
            if(userList.size() <1){
                return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
        }
        return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
    }

    @RolesAllowed("SuperAdministrator")
    @GetMapping("/admin/user/center/{center_id}/{role}")
    public ResponseEntity<List<User>> getByDistinctByRoleAndCenter(@PathVariable Role role, Long centerId ){
        List<User> userList = userRep.findDistinctByRoleAndCenterId(role, centerId);
        if(userList.size() <1){
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @RolesAllowed("SuperAdministrator")
    @PostMapping("/admin/user/")
    public ResponseEntity<User> newUser (@RequestParam String firstName, String lastName, String email, String phone, Long centerId, String password){
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        Optional<Center> center = centerRep.findById(centerId);
        if (!center.isPresent())
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        newUser.setCenter(center.get());
        userRep.save(newUser);
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

    @RolesAllowed("SuperAdministrator")
    @PostMapping("/admin/user/SuperAdministrator")
    public void newSuperAdministrator (){
        userService.createSuperAdminDefault();
    }

    @RolesAllowed("SuperAdministrator")
    @PostMapping("/admin/user/Administrator")
    public void newAdministrator (){
        userService.createAdminDefault();
    }

    @RolesAllowed("SuperAdministrator")
    @PostMapping("/admin/user/Doctor")
    public void newSDoctor (){
        userService.createDoctorDefault();
    }


    @PutMapping("/admin/user/{id}")
    public ResponseEntity<User> updateUser(@RequestParam String firstName, String lastName, Long centerId, @PathVariable long id){
        Optional <User> target = userRep.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRep.findByEmail(authentication.getName());
        Optional<Center> center = centerRep.findById(centerId);
        if(!center.isPresent() || !target.isPresent() || !user.isPresent() || user.get().getRole() != Role.SuperAdministrator && !(target.get().getCenter().equals(user.get().getCenter()) && user.get().getRole() == Role.Administrator)) 
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        target.get().setFirstName(firstName);
        target.get().setLastName(lastName);
        target.get().setCenter(center.get());
        userRep.save(target.get());
        return new ResponseEntity<User>(target.get(),HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        Optional <User> target = userRep.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRep.findByEmail(authentication.getName());
        if(!target.isPresent() || !user.isPresent() || user.get().getRole() != Role.SuperAdministrator && !(target.get().getCenter().equals(user.get().getCenter()) && user.get().getRole() == Role.Administrator)) 
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        target.get().setDisabled(true);
        userRep.save(target.get());
        return new ResponseEntity<User>(target.get(), HttpStatus.OK);
    }
}