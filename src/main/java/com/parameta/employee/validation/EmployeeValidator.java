package com.parameta.employee.validation;

import java.time.LocalDate;
import java.time.Period;

import com.parameta.employee.entity.Employee;

public class EmployeeValidator {
	
	public static void employeeValidation(Employee employee) {
		if (employee.getName() == null || employee.getLastName() == null ||
	            employee.getBirthday() == null || employee.getStartJobDate() == null
	            || employee.getDocumentId() == null) {
	            throw new IllegalArgumentException("Fields cannot be empty");
	        }
		
		Period age = Period.between(employee.getBirthday(), LocalDate.now());
        if (age.getYears() < 18) {
            throw new IllegalArgumentException("The employee must be of legal age");
        }
	}
}
