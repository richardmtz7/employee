package com.parameta.employee.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.parameta.employee.entity.Employee;
import com.parameta.employee.entity.DTO.EmployeeResponse;

public interface EmployeeService {
	Employee createEmployee(Employee employee);

	Optional<Employee> getEmployee(Integer employeeId);
	
	String calculateAge(LocalDate birthday);

	String calculateVinculationDate(LocalDate startJobDate);
	
	List<EmployeeResponse> getAllEmployees();
	
	void deactivateOrActivateEmployeeById(Integer employeeId);
}
