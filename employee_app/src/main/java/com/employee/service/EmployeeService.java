package com.employee.service;

import java.util.List;

import com.employee.dto.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO getEmployee(Long id) ;

	void saveEmployee(EmployeeDTO empDto) throws Exception;

	void updateEmployee(EmployeeDTO empDto) throws Exception;

	void deleteEmployee(Long id) throws Exception;


}
