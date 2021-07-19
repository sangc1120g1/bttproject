package com.example.test.service.impl;


import com.example.test.model.Customer;
import com.example.test.model.Role;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.security.CustomUserDetails;
import com.example.test.service.base.RoleService;
import com.example.test.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);
        if (user.isPresent()) {
            return CustomUserDetails.build(user.get());
        }
        throw new UsernameNotFoundException(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByCreateDate(String date) {
        return null;
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    public User saveUser(User user, String role) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getUsername());
        if (userOptional.isPresent()) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> roleOptional = roleService.findByName(role);
        if (!roleOptional.isPresent()) {
            roleService.save(new Role("ROLE_" + role));
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleOptional.get());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User CreateUser(String username, String typeUser, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("Btt@1234");
        user.setTypeOfUser(typeUser);
        return saveUser(user, role);
    }
}
