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

import com.employee.dto.EmployeeDTO;
import com.employee.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getAllEmployees() {
		List<EmployeeDTO> empList = employeeService.getAllEmployees();
		if (empList == null) {
			return new ResponseEntity<Error>(new Error("No data found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<EmployeeDTO>>(empList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployee(@PathVariable("id") String str) {
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"), HttpStatus.NOT_FOUND);
		}
		EmployeeDTO empDto = employeeService.getEmployee(id);
		if(empDto==null){
			return new ResponseEntity<Error>(new Error("Employee not found"),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EmployeeDTO>(empDto,HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO empDto){
		try{
			employeeService.saveEmployee(empDto);
		}catch(Exception e){
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO empDto, @PathVariable("id") String str){
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"),HttpStatus.NOT_FOUND);
		}
		try{
			empDto.setId(id);
			employeeService.updateEmployee(empDto);
		}catch(Exception e){
			return new ResponseEntity<Error>(new Error(e.getMessage()),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") String str){
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"), HttpStatus.NOT_FOUND);
		}
		try{
		employeeService.deleteEmployee(id);
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
