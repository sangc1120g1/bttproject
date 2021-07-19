package com.example.test.service.base;



import com.example.test.model.User;
import com.example.test.service.CommonService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService, CommonService<User> {
    Optional<User> findUserByEmail(String email);
    public User CreateUser(String username,String typeOfUser,String role);
}
