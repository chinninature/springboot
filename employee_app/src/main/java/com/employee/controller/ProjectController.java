package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.employee.dto.ProjectDTO;
import com.employee.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getAllProjects() {
		List<ProjectDTO> projectList = projectService.getAllProjects();
		if (projectList == null) {
			return new ResponseEntity<Error>(new Error("No data found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ProjectDTO>>(projectList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list_name", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getAllProjectsWithEmpName() {
		List<ProjectDTO> projectList = projectService.getAllProjectsWithEmpName();
		if (projectList == null) {
			return new ResponseEntity<Error>(new Error("No data found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ProjectDTO>>(projectList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProject(@PathVariable("id") String str) {
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"), HttpStatus.NOT_FOUND);
		}
		ProjectDTO projectDto = projectService.getProject(id);
		if(projectDto==null){
			return new ResponseEntity<Error>(new Error("Employee not found"),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProjectDTO>(projectDto,HttpStatus.OK);
		
	}
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveProject(@RequestBody ProjectDTO projectDto){
		try{
			projectService.saveProject(projectDto);
		}catch(Exception e){
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.PUT)
	public ResponseEntity<?> updateProject(@RequestBody ProjectDTO projectDto, @PathVariable("id") String str){
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"),HttpStatus.NOT_FOUND);
		}
		try{
			projectDto.setId(id);
			projectService.updateProject(projectDto);
		}catch(Exception e){
			return new ResponseEntity<Error>(new Error(e.getMessage()),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable("id") String str){
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"), HttpStatus.NOT_FOUND);
		}
		try{
		projectService.deleteProject(id);
		}catch(Exception e){
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	class Error {
		private String message;

		public Error(String message) {
			super();
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
