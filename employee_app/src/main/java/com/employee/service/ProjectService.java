package com.employee.service;

import java.util.List;

import com.employee.dto.ProjectDTO;

public interface ProjectService {

	List<ProjectDTO> getAllProjects();

	ProjectDTO getProject(Long id) ;

	void saveProject(ProjectDTO projectDto) throws Exception;

	void updateProject(ProjectDTO projectDto) throws Exception;

	void deleteProject(Long id) throws Exception;

	List<ProjectDTO> getAllProjectsWithEmpName();


}
