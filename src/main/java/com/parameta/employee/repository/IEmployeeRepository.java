package com.parameta.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parameta.employee.entity.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{

}
