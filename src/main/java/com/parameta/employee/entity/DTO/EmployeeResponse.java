package com.parameta.employee.entity.DTO;


import jakarta.validation.constraints.NotBlank;

public record EmployeeResponse(
		@NotBlank String name,
		@NotBlank String age,
		@NotBlank String timeInCompany) {
}
