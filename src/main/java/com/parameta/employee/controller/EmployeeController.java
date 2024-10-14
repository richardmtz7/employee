package com.parameta.employee.controller;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parameta.employee.entity.Employee;
import com.parameta.employee.entity.DTO.EmployeeResponse;
import com.parameta.employee.service.EmployeeService;
import com.parameta.employee.util.Utils;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Employee> createEmployee(
			@RequestParam String name,
			@RequestParam String lastName,
			@RequestParam String position,
			@RequestParam Double salary,
			@RequestParam String birthday,
			@RequestParam String startJobDate,
			@RequestParam String typeDocId,
			@RequestParam String documentId) throws Exception {
		try {
			Employee employeeFormat = Employee.builder()
					.name(name)
					.lastName(lastName)
					.position(position)
					.salary(salary)
					.startJobDate(Utils.formatDate(startJobDate))
					.birthday(Utils.formatDate(birthday))
					.typeDocId(typeDocId)
					.documentId(documentId)
					.build();

			Employee employeeResponse = employeeService.createEmployee(employeeFormat);
			return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Integer id){
		try {
			
			Optional<Employee> employeeObject = employeeService.getEmployee(id);
			
			String age = employeeService.calculateAge(employeeObject.get().getBirthday());
			String startJobDate = employeeService.calculateVinculationDate(employeeObject.get().getStartJobDate());
			String name = employeeObject.get().getName().concat(" " + employeeObject.get().getLastName());
			EmployeeResponse response = new EmployeeResponse(name , age, startJobDate);
			return new ResponseEntity<>(response, HttpStatus.FOUND);
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
		try {
			List<EmployeeResponse> employees = employeeService.getAllEmployees();
			return new ResponseEntity<>(employees, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/change-status/{id}")
	public ResponseEntity<String> deactivateEmployeeById(@PathVariable Integer id){
		try {
			employeeService.deactivateOrActivateEmployeeById(id);
			
			return new ResponseEntity<>("Status has been change", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
