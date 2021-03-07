package com.mustafauzunal.springbootprojectcreator.service;


import java.util.List;

import com.mustafauzunal.springbootprojectcreator.model.User;
import com.mustafauzunal.springbootprojectcreator.web.dto.UserRegistrationDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

    User save(UserRegistrationDTO userRegistrationDTO);
    List <User> getAllUsers();
    
}
