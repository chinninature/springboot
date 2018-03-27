package com.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProjectDTO {
	private Long id;
	private String name;
	private String description;
	private EmployeeDTO employee;

	public ProjectDTO() {
		super();
	}

	public ProjectDTO(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public ProjectDTO(Long id, String name, String description, EmployeeDTO employee) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.employee = employee;
	}

	public ProjectDTO(Long id, String name, String description, String empName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.employee = new EmployeeDTO(empName);
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "ProjectDTO [id=" + id + ", name=" + name + ", description=" + description + ", employee=" + employee
				+ "]";
	}

}
