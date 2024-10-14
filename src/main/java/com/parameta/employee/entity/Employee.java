package com.parameta.employee.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "typeDocId")
	private String typeDocId;
	
	@Column(name = "documentId")
	private String documentId;
	
	@Column(name = "birthday")
	private LocalDate birthday;
	
	@Column(name = "startJobDate")
	private LocalDate startJobDate;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "salary")
	private Double salary;
	
	@Builder.Default
	@Column(name = "status")
	private String status = "1";
}
