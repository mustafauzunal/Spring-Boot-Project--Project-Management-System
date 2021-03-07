package com.mustafauzunal.springbootprojectcreator.service;

import java.util.List;
import java.util.Optional;

import com.mustafauzunal.springbootprojectcreator.model.Projects;
import com.mustafauzunal.springbootprojectcreator.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    
	@Autowired
    private ProjectRepository projectRepository;

	@Autowired
    private JavaMailSender mailSender;

	//Method for listing all projects
	@Override
	public List<Projects> getAllProjects() {
		
		return projectRepository.findAll();
	}

	//Method for creatingproject
	@Override
	public	void saveProjects(Projects projects) {
		projectRepository.save(projects);
		
	}

	//Method for getting project by id
	@Override
	public Projects getProjectsById(long id) {
		Optional<Projects> optional = projectRepository.findById(id);
		Projects projects = null;
        if(optional.isPresent()){
            projects = optional.get();
        }else{
            throw new RuntimeException("Project not found for id : " + id);
        }
        return projects;
	}


	//Method for deleting project
	@Override
	public void deleteProjectsById(long id) {
		projectRepository.deleteById(id);
		
	}

	//Method for getting email
	@Override
	public String getProjectsByIdForEmail(Projects project) {
		Long projectId = project.getId();
		Optional<Projects> optional = projectRepository.findById(projectId);
		String emails = null;
        if(optional.isPresent()){
    		emails = optional.get().getEmail();
			
		}else{
            throw new RuntimeException("Project not found for id : " + projectId);
        }
        return emails;
	}

	//Method for getting project name
    @Override
	public String getProjectsByIdForName(Projects project) {
		Long projectId = project.getId();
		Optional<Projects> optional = projectRepository.findById(projectId);
		String name = null;
        if(optional.isPresent()){
            name = optional.get().getProjectName();
        }else{
            throw new RuntimeException("Project not found for id : " + projectId);
        }
        return name;
	}

	//Method for getting project description
	@Override
	public String getProjectsByIdForDesc(Projects project) {
		Long projectId = project.getId();
		Optional<Projects> optional = projectRepository.findById(projectId);
		String desc = null;
        if(optional.isPresent()){
            desc = optional.get().getProjectDescription();
        }else{
            throw new RuntimeException("Project not found for id : " + projectId);
        }
        return desc;
	}
	
	//Method for sending mails
	@Override
	public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);	
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
	//MEthod for updating projects
	@Override
	public Projects updateProjects(Projects projects) {
		long projectID = projects.getId();
		Optional<Projects> currentCustomer = projectRepository.findById(projectID);
		if(currentCustomer.isPresent()){
			currentCustomer.get().setProjectName(projects.getProjectName());;
			currentCustomer.get().setProjectDescription(projects.getProjectDescription());
			currentCustomer.get().setPeopleAssignedToTheProject(projects.getPeopleAssignedToTheProject());
			currentCustomer.get().setEmail(projects.getEmail());
			
			Projects savedCustomer = projectRepository.save(currentCustomer.get());
			
			return savedCustomer;
		}
		return null;
		
	}
    
}
