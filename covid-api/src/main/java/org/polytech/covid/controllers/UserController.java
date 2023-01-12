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

import org.polytech.covid.entities.User;
import org.polytech.covid.entities.Role;
import org.polytech.covid.repositories.UserRepository;
import org.polytech.covid.entities.Center;

@RestController
@RequestMapping("/admin/user")
@RolesAllowed("SuperAdministrator")
public class UserController {
    @Autowired
    private UserRepository userRep;

    @GetMapping("")
    public List<User> list(){
        return userRep.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getByid(@PathVariable long id){
        try{
            User user = userRep.findById(id).orElseThrow();
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getbyEmail(@PathVariable String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRep.findByEmail(authentication.getName());
        if(!user.isPresent() || user.get().getRole() != Role.SuperAdministrator && !email.equals(user.get().getEmail())) 
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getByRole(@PathVariable String role){
        List<User> userList = userRep.findByRole(Role.valueOf(role));
        if(userList.size() <1){
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/center/{center_id}/{role}")
    public ResponseEntity<List<User>> getByDistinctByRoleAndCenter(@PathVariable Role role, Long center_id ){
        List<User> userList = userRep.findDistinctByRoleAndCenter_Id(role, center_id);
        if(userList.size() <1){
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> newUser (@RequestParam String firstName, String lastName, String email, String phone, Center center){
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setCenter(center);
        userRep.save(newUser);
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestParam String firstName, String lastName, Center center, @PathVariable long id){
        User user = userRep.findById(id).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCenter(center);
        userRep.save(user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        User user = userRep.findById(id).orElseThrow();
        user.setDisabled(true);
        userRep.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}