package com.mustafauzunal.springbootprojectcreator.service;


import java.util.List;

import com.mustafauzunal.springbootprojectcreator.model.Projects;


public interface ProjectService {

    List <Projects> getAllProjects();
    void saveProjects(Projects projects);
    Projects updateProjects(Projects projects);
    Projects getProjectsById(long id);
    String getProjectsByIdForEmail(Projects project);
    String getProjectsByIdForName(Projects project);
    String getProjectsByIdForDesc(Projects project);
    void deleteProjectsById(long id);
    void sendMail(String to, String subject, String body);
	
    
}
