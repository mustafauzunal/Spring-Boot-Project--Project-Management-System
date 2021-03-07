package com.mustafauzunal.springbootprojectcreator.controller;

import com.mustafauzunal.springbootprojectcreator.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class MainController {
    
    @Autowired
	public ProjectService projectService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView home(Model model ) {
	    model.addAttribute("listProjects", projectService.getAllProjects());  
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
	}

}
