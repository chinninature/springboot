package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("SELECT emp FROM Employee emp WHERE id= :givenId")
	Employee findByEmpId(@Param("givenId") Long id);
	
	@Query("SELECT emp FROM Employee emp WHERE name= :givenName")
	Employee findByName(@Param("givenName") String name);
}
