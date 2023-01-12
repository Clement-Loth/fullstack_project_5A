package org.polytech.covid.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.polytech.covid.entities.Center;
import org.polytech.covid.entities.MyUserDetails;
import org.polytech.covid.entities.Role;
import org.polytech.covid.entities.User;
import org.polytech.covid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getEmployees() {
        ArrayList<User> employees = new ArrayList<>();
        this.userRepository.findAll().forEach(employees::add);
        return employees;
    }

    public List<User> getAdministrators(){
        return this.userRepository.findByRoleAndDisabledFalse(Role.Administrator);
    }

    public List<User> getDoctorsByCenter(Center center){
        return this.userRepository.findDistinctByRoleAndCenter_IdAndDisabledFalse(
                Role.Doctor,
                center.getId()
        );
    }

    @PostConstruct
    public void createSuperAdminDefault() {
        if(userRepository.findByEmailAndDisabledFalse("test@toto.fr").isEmpty()){
            User superAdmin = new User();
            superAdmin.setEmail("test@toto.fr");
            superAdmin.setFirstName("Test");
            superAdmin.setLastName("Test");
            superAdmin.setPassword(passwordEncoder.encode("toto"));
            superAdmin.setPhone("0");
            superAdmin.setRole(Role.SuperAdministrator);
            this.userRepository.save(superAdmin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException{

        Optional<User> user = this.userRepository.findByEmailAndDisabledFalse(username);
        if(user.isPresent()){
            return new MyUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("L'utilisateur " + username +" n'existe pas.");
        }
    }

}
