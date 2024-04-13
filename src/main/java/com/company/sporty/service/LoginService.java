package com.company.sporty.service;


import com.company.sporty.entity.Login;
import com.company.sporty.entity.LoginRegistry;
import com.company.sporty.repository.LoginRegistryRepository;
import com.company.sporty.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService{

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    LoginRegistryRepository loginRegistryRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${admin.credentials.username}")
    private String adminUsername;

    @Value("${admin.credentials.password}")
    private String adminPassword;

    @Value("${admin.credentials.roles}")
    private String adminRoles;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Login> result = loginRepository.findUserByName(username);
        if(result.isPresent()) {

            Login ll  = result.get();

            // Save user log
            LoginRegistry loginRegistry = new LoginRegistry();
            loginRegistry.setUsername(username);
            loginRegistry.setLdt(LocalDateTime.now());
            loginRegistryRepository.save(loginRegistry);

            return User.builder().username(ll.getUsername()).password(ll.getPassword()).roles(getRoles(ll)).build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public String[] getRoles(Login ll) {
        if(ll.getRole() == null) {
            return new String[] {"USER"};
        }else {
            return ll.getRole().split(",");
        }
    }

    public void createAdminAccount() {

        Login adminLogin = new Login();
        adminLogin.setUsername(adminUsername);
        String password = passwordEncoder.encode(adminPassword);
        adminLogin.setPassword(password);
        adminLogin.setRole(adminRoles);

        Optional<Login> result = loginRepository.findUserByName(adminLogin.getUsername());
        if(result.isPresent()) {
            System.out.println("Admin user already exists");
        }else {
            loginRepository.save(adminLogin);
            System.out.println("Admin user account created successfully");
        }
    }

    public String createUserAccount(Login login) {
        Optional<Login> result = loginRepository.findUserByName(login.getUsername());
        // User role
        login.setRole("USER");

        if(result.isPresent()) {
            return "User already exists";
        } else {
            loginRepository.save(login);
            return "Account created successfully";
        }
    }

    public List<Login> getAllUsers() {
        return loginRepository.findAll();
    }
}
