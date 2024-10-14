package com.parameta.employee.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parameta.employee.entity.Employee;
import com.parameta.employee.entity.DTO.EmployeeResponse;
import com.parameta.employee.repository.IEmployeeRepository;
import com.parameta.employee.service.EmployeeService;
import com.parameta.employee.validation.EmployeeValidator;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private IEmployeeRepository employeeRepository;

	public EmployeeServiceImpl(IEmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		EmployeeValidator.employeeValidation(employee);
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public Optional<Employee> getEmployee(Integer employeeId) {
		return employeeRepository.findById(employeeId);
	}

	@Override
	public String calculateAge(LocalDate birthday) {
		Period age = Period.between(birthday, LocalDate.now());

		int year = age.getYears();
		int month = age.getMonths();
		int days = age.getDays();

		String formattedAge = String.format("%d años, %d meses y %d días", year, month, days);
		return formattedAge;
	}

	@Override
	public String calculateVinculationDate(LocalDate startJobDate) {
		Period vinculationDate = Period.between(startJobDate, LocalDate.now());

		int year = vinculationDate.getYears();
		int month = vinculationDate.getMonths();
		int days = vinculationDate.getDays();

		String formattedVinculationDate = String.format("%d años, %d meses y %d días", year, month, days);

		return formattedVinculationDate;
	}

	@Override
	public List<EmployeeResponse> getAllEmployees() {
		
		List<EmployeeResponse> listEmployee = new ArrayList<>();
		
		List<Employee> employeeObjectList = employeeRepository.findAll();
		
		if(!employeeObjectList.isEmpty()) {
			for (int i = 0; i < employeeObjectList.size(); i ++) {
				String ages = calculateAge(employeeObjectList.get(i).getBirthday());
				String startJobDate = calculateVinculationDate(employeeObjectList.get(i).getStartJobDate());
				String names = employeeObjectList.get(i).getName().concat(" " + employeeObjectList.get(i).getLastName());
				EmployeeResponse employeeResponseObject = new EmployeeResponse(names, ages, startJobDate);
				listEmployee.add(employeeResponseObject);
			}
		}
		
		return listEmployee;
	}

	@Override
	public void deactivateOrActivateEmployeeById(Integer employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		
		if(employee != null) {
			if(employee.getStatus().equals("1")) {
				employee.setStatus("0");
			} else {
				employee.setStatus("1");
			}
			employeeRepository.save(employee);
		}
		
	}
}
