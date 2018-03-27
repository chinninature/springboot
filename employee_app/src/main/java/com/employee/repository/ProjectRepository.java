package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.domain.Employee;
import com.employee.domain.Project;
import com.employee.dto.ProjectDTO;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("SELECT project FROM Project project WHERE id= :givenId")
	Project findByProjectId(@Param("givenId") Long id);

	@Query("SELECT project FROM Project project WHERE name= :givenName")
	Project findByName(@Param("givenName") String name);

	@Query("SELECT project FROM Project project"
			+ " INNER JOIN FETCH project.employee  emp ORDER BY project.name ASC")
	List<Project> findAllProjectsWithEmployee();

	@Query("SELECT new com.employee.dto.ProjectDTO(prj.id,prj.name,prj.description, emp.name) "
			+ " FROM Project prj INNER JOIN prj.employee  emp")
	List<ProjectDTO> getAllProjectsWithEmpName();

	@Query("SELECT emp FROM Employee emp WHERE id= :givenId")
	Employee findEmployeeByProjectId(Long id);
}
