package com.mustafauzunal.springbootprojectcreator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustafauzunal.springbootprojectcreator.model.Projects;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SpringBootProjectCreatorApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;


	@Test
	void contextLoads() {
	}
	

	//Test to get all projects
	@Test
	public void testGetAllProjects() throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
											.andExpect(MockMvcResultMatchers.view().name("index"))
											.andExpect(MockMvcResultMatchers.model().attributeExists("listProjects"))
											.andExpect(status().isOk())
											.andDo(MockMvcResultHandlers.print())
											.andReturn();

		Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$[*].id"));
		Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$[*].projectName"));
		Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$[*].projectDescription"));
		Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$[*].peopleAssignedToTheProject"));
	
	}

	//Test to create and save new project
	@Test
	public void testSaveProject() throws Exception{
			Projects newProject = new Projects();
			newProject.setProjectName("Project 3");
			newProject.setProjectDescription("Project 3 Description");
			newProject.setPeopleAssignedToTheProject("Mustafa Uzunal");
			newProject.setEmail("mustafa.uzunal@yandex.com");

			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/saveProject").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("projectName", newProject.getProjectName()).param("projectDescription", newProject.getProjectDescription()).param("peopleAssignedToTheProject", newProject.getPeopleAssignedToTheProject()).param("email", newProject.getEmail()))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
			
			Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.id"));
			Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.projectName"));
			Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.projectDescription"));
			Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.peopleAssignedToTheProject"));
	


	}

	//Test to update an existing project
	@Test
	public void testUpdateProject() throws Exception{
			Projects newProject = new Projects();
			newProject.setId(1L);
			newProject.setProjectName("Project 4");
			newProject.setProjectDescription("Project 4 Description");
			newProject.setPeopleAssignedToTheProject("Mustafa Uzunal");
			newProject.setEmail("mustafa.uzunal@yandex.com");
			

			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/updateProject").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("id", newProject.getId().toString()).param("projectName", newProject.getProjectName()).param("projectDescription", newProject.getProjectDescription()).param("peopleAssignedToTheProject", newProject.getPeopleAssignedToTheProject()).param("email", newProject.getEmail()))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();

			Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.id"));
			Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.projectName"));
			Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.projectDescription"));
			Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.peopleAssignedToTheProject"));



	}

	//Test to get project by id
	@Test
	public void testGetProjectById() throws Exception {
		Long projectId = 1L;
		
		MvcResult  result = mockMvc.perform(MockMvcRequestBuilders.get("/showFormForUpdate/{id}" , projectId)
							.accept(MediaType.APPLICATION_JSON))
							.andDo(MockMvcResultHandlers.print())
							.andExpect(status().isOk())
							.andReturn();
							
							Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.id"));
							Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.projectName"));
							Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.projectDescription"));
							Assert.assertNotNull(result.getModelAndView().getModel().containsValue("$.peopleAssignedToTheProject"));					
							
						

		
	}


	//Test to delete project
	@Test
	public void testDeleteProjectsById() throws Exception{
			Long projectId = 2L;

			mockMvc.perform(MockMvcRequestBuilders.get("/deleteProject/{id}" , projectId)
									.accept(MediaType.APPLICATION_FORM_URLENCODED))
									.andExpect(status().is3xxRedirection());


	}






	public static String asJsonString(final Object object){
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
