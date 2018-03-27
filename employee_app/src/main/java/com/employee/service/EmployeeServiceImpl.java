package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.domain.Employee;
import com.employee.dto.EmployeeDTO;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> empList = employeeRepository.findAll();
		if (!CollectionUtils.isEmpty(empList)) {
			List<EmployeeDTO> dtoList = new ArrayList<EmployeeDTO>();
			for (Employee emp : empList) {
				dtoList.add(new EmployeeDTO(emp.getId(), emp.getName(), emp.getAddress(), emp.getSalary(),
						emp.getCompany()));
			}
			return dtoList;
		}
		return null;
	}

	@Override
	public EmployeeDTO getEmployee(Long id){

		Employee emp = employeeRepository.findByEmpId(id);
		EmployeeDTO empDto = null;
		if (emp != null) {
			empDto = new EmployeeDTO(emp.getId(), emp.getName(), emp.getAddress(), emp.getSalary(), emp.getCompany());
		}
		return empDto;
	}

	@Override
	public void saveEmployee(EmployeeDTO empDto) throws Exception {
		Employee emp = employeeRepository.findByName(empDto.getName());
		if(emp!=null){
			throw new Exception("Employee already exists");
		}
		Employee empObj = new Employee(null,empDto.getName(), empDto.getAddres(), empDto.getSalary(), empDto.getCompany());
		employeeRepository.save(empObj);
	}

	@Override
	public void updateEmployee(EmployeeDTO empDto) throws Exception {
		Employee emp = employeeRepository.findByEmpId(empDto.getId());
		if(emp==null){
			throw new Exception("Employee not found");
		}
		emp.setId(empDto.getId());
		emp.setName(empDto.getName());
		emp.setAddress(empDto.getAddres());
		emp.setSalary(empDto.getSalary());
		emp.setCompany(empDto.getCompany());
		employeeRepository.save(emp);
		
	}

	@Override
	public void deleteEmployee(Long id) throws Exception {
		Employee emp = employeeRepository.findByEmpId(id);
		if(emp==null){
			throw new Exception("Employee not found");
		}
		employeeRepository.delete(emp);
		
	}
	
	
	
	

}
