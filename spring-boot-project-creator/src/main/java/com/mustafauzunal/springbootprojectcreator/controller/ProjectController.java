package com.mustafauzunal.springbootprojectcreator.controller;

import java.util.ArrayList;
import java.util.List;


import com.mustafauzunal.springbootprojectcreator.model.Projects;
import com.mustafauzunal.springbootprojectcreator.service.ProjectService;
import com.mustafauzunal.springbootprojectcreator.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/showNewProjectsForm", method = RequestMethod.GET)
    public ModelAndView showNewEmployeeForm(Model model) {
        Projects project = new Projects();
        model.addAttribute("project", project);
        model.addAttribute("listUser", userService.getAllUsers());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new_project");
        return modelAndView;
    }
    @RequestMapping(value="/saveProject", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public ModelAndView saveProject(@ModelAttribute("project") Projects project) {
        projectService.saveProjects(project);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new_project");
        return modelAndView;
       
    }
    @RequestMapping(value="/updateProject", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public ModelAndView updateProject(@ModelAttribute("project") Projects project, Model model) {
        projectService.updateProjects(project);
        model.addAttribute("listUser", userService.getAllUsers());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update_project");
        return modelAndView;
    }


    @RequestMapping(value="/showFormForUpdate/{id}", method = RequestMethod.GET)
    public ModelAndView showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        Projects project = projectService.getProjectsById(id);
        model.addAttribute("project", project);
        model.addAttribute("listUser", userService.getAllUsers());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update_project");
        return modelAndView;
    }
    @RequestMapping(value="/deleteProject/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProject(@PathVariable(value = "id") long id) {
        this.projectService.deleteProjectsById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
      
    }
    @RequestMapping(value="/sendEmail", method = RequestMethod.GET)
    public ModelAndView sendEmail(@ModelAttribute("project") Projects project){
        String emails = projectService.getProjectsByIdForEmail(project);
        String names = projectService.getProjectsByIdForName(project);
        String desc = projectService.getProjectsByIdForDesc(project);
        List<String> list=new ArrayList<String>();
        String [] arrSplit = emails.split(",");
        for (String arrSplit1 : arrSplit) {
           list.add(arrSplit1); 
        }
        for(String listSplit : list){
            projectService.sendMail(listSplit,names,desc);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
    
