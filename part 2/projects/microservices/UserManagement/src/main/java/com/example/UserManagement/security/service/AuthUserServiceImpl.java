package com.example.UserManagement.security.service;

import com.example.UserManagement.domain.entities.Costumer;
import com.example.UserManagement.domain.entities.Manager;
import com.example.UserManagement.domain.valueObjects.user.Username;
import com.example.UserManagement.repository.CostumerRepository;
import com.example.UserManagement.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthUserServiceImpl implements UserDetailsService {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private CostumerRepository costumerRepository;

    public AuthUserServiceImpl(ManagerRepository managerRepository, CostumerRepository costumerRepository) {
        this.managerRepository = managerRepository;
        this.costumerRepository = costumerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<Manager> managerOptional = managerRepository.findByUsername(new Username(username));
            if (managerOptional.isPresent()) {
                Manager manager = managerOptional.get();
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(manager.getRole().getDescription()));

                return new org.springframework.security.core.userdetails.User(manager.getUsername().getUsername(), manager.getPassword().getPassword(),
                        authorities);
            }
            Optional<Costumer> costumerOptional = costumerRepository.findByUsername(new Username(username));
            if (costumerOptional.isPresent()) {
                Costumer costumer = costumerOptional.get();
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(costumer.getRole().getDescription()));

                return new org.springframework.security.core.userdetails.User(costumer.getUsername().getUsername(), costumer.getPassword().getPassword(),
                        authorities);
            }
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return null;
    }
}
