package com.mustafauzunal.springbootprojectcreator.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Entity 
@Data
@Table(name = "projects")
public class Projects {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "people_assigned_to_the_project")
    private String peopleAssignedToTheProject;
    
    
    @Column(name = "emails_of_people")
    String email;


	public Projects(String projectName, String projectDescription, String peopleAssignedToTheProject, String email) {
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.peopleAssignedToTheProject = peopleAssignedToTheProject;
		this.email = email;
	}


	public Projects() {
	}
    
    
}
