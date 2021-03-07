package com.mustafauzunal.springbootprojectcreator.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mustafauzunal.springbootprojectcreator.model.Role;
import com.mustafauzunal.springbootprojectcreator.model.User;
import com.mustafauzunal.springbootprojectcreator.repository.UserRepository;
import com.mustafauzunal.springbootprojectcreator.web.dto.UserRegistrationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository) {
		super();
        this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDTO userRegistrationDTO) {
		User user = new User(userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(), userRegistrationDTO.getEmail(), passwordEncoder.encode(userRegistrationDTO.getPassword()),Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
    
	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

    
}