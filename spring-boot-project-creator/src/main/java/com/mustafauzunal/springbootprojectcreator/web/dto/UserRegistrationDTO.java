package com.mustafauzunal.springbootprojectcreator.web.dto;

import lombok.Data;


@Data
public class UserRegistrationDTO {
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
	
    public UserRegistrationDTO(String firstName, String lastName, String email, String password) {
		super();
        this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public UserRegistrationDTO() {
	}

    
}
