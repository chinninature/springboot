package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.domain.Employee;
import com.employee.domain.Project;
import com.employee.dto.EmployeeDTO;
import com.employee.dto.ProjectDTO;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired 
	EmployeeRepository employeeRepository;

	@Override
	public List<ProjectDTO> getAllProjects() {
		List<Project> projectList = projectRepository.findAllProjectsWithEmployee();
		if (!CollectionUtils.isEmpty(projectList)) {
			List<ProjectDTO> dtoList = new ArrayList<ProjectDTO>();
			for (Project project : projectList) {
				EmployeeDTO empDto = null;
				if (project.getEmployee() != null) {
					Employee emp = project.getEmployee();
					empDto = new EmployeeDTO(emp.getId(), emp.getName(), emp.getAddress(), emp.getSalary(),
							emp.getCompany());
				}
				dtoList.add(new ProjectDTO(project.getId(), project.getName(), project.getDescription(), empDto));
			}
			return dtoList;
		}
		return null;
	}
	
	@Override
	public List<ProjectDTO> getAllProjectsWithEmpName() {
		List<ProjectDTO> dtoList = projectRepository.getAllProjectsWithEmpName();
		return dtoList;
	}

	@Override
	public ProjectDTO getProject(Long id) {

		Project project = projectRepository.findByProjectId(id);
		EmployeeDTO empDto = null;
		if (project.getEmployee() != null) {
			Employee emp = project.getEmployee();
			empDto = new EmployeeDTO(emp.getId(), emp.getName(), emp.getAddress(), emp.getSalary(),
					emp.getCompany());
		}
		ProjectDTO projectDto = new ProjectDTO(project.getId(), project.getName(), project.getDescription(), empDto);
		return projectDto;
	}

	@Override
	public void saveProject(ProjectDTO projectDto) throws Exception {
		Employee emp = null;
		if(projectDto.getEmployee().getId()==null || projectDto.getEmployee().getId()==0){
			emp = new Employee(null, projectDto.getEmployee().getName(),
					projectDto.getEmployee().getAddres(), projectDto.getEmployee().getSalary(),
					projectDto.getEmployee().getCompany());
		}else{
			emp = employeeRepository.findByEmpId(projectDto.getEmployee().getId());
		}
		
		Project projectObj = new Project(null, projectDto.getName(), projectDto.getDescription(),emp);
		projectRepository.save(projectObj);
	}

	@Override
	public void updateProject(ProjectDTO projectDto) throws Exception {
		Project project = projectRepository.findByProjectId(projectDto.getId());
		if (project == null) {
			throw new Exception("Employee not found");
		}
		project.setId(projectDto.getId());
		project.setName(projectDto.getName());
		project.setDescription(projectDto.getDescription());
		projectRepository.save(project);

	}

	@Override
	public void deleteProject(Long id) throws Exception {
		Project project = projectRepository.findByProjectId(id);
		if (project == null) {
			throw new Exception("Employee not found");
		}
		projectRepository.delete(project);

	}

}
