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
        return this.userRepository.findByRole(Role.Administrator);
    }

    public List<User> getDoctorsByCenter(Center center){
        return this.userRepository.findDistinctByRoleAndCenter_Id(
                Role.Doctor,
                center.getId()
        );
    }

    @PostConstruct
    public void createSuperAdminDefault() {
        createUser("test@toto.fr","toto","Test","Test","0",Role.SuperAdministrator);
    }

    @PostConstruct
    public void createAdminDefault() {
        createUser("admin@toto.fr","toto","Test","Test","0",Role.Administrator);
    }

    @PostConstruct
    public void createDoctorDefault() {
        createUser("doc@toto.fr","toto","Test","Test","0",Role.Doctor);
    }

    public boolean createUser(String email, String password, String firstName, String lastName, String phone, Role role) {
        if(userRepository.findByEmail(email).isEmpty()){
            User superAdmin = new User();
            superAdmin.setEmail(email);
            superAdmin.setFirstName(firstName);
            superAdmin.setLastName(lastName);
            superAdmin.setPassword(passwordEncoder.encode(password));
            superAdmin.setPhone(phone);
            superAdmin.setRole(role);
            this.userRepository.save(superAdmin);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException{

        Optional<User> user = this.userRepository.findByEmail(username);
        if(user.isPresent()){
            return new MyUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("L'utilisateur " + username +" n'existe pas.");
        }
    }

}
