package com.parameta.employee.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private String message;
	private Integer status;
	private Long timestamp;
}
