package com.parameta.employee.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.parameta.employee.entity.Employee;
import com.parameta.employee.entity.DTO.EmployeeResponse;
import com.parameta.employee.repository.IEmployeeRepository;
import com.parameta.employee.service.impl.EmployeeServiceImpl;

public class EmployeeServiceTest {
	@Mock
    private IEmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        Employee employee = Employee.builder()
                .name("John")
                .lastName("Doe")
                .typeDocId("DNI")
                .documentId("12345678")
                .birthday(LocalDate.of(1990, 1, 1))
                .startJobDate(LocalDate.of(2020, 1, 1))
                .position("Developer")
                .salary(50000.00)
                .status("1")
                .build();

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployee(1);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
    }

    @Test
    void testCalculateAge() {
        LocalDate birthday = LocalDate.of(1990, 10, 10);
        String age = employeeService.calculateAge(birthday);

        assertTrue(age.contains("años"));
        assertTrue(age.contains("meses"));
        assertTrue(age.contains("días"));
    }

    @Test
    void testCalculateVinculationDate() {
        LocalDate startJobDate = LocalDate.of(2015, 1, 1);
        String vinculationDate = employeeService.calculateVinculationDate(startJobDate);

        assertTrue(vinculationDate.contains("años"));
        assertTrue(vinculationDate.contains("meses"));
        assertTrue(vinculationDate.contains("días"));
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setName("John");
        employee1.setLastName("Doe");
        employee1.setBirthday(LocalDate.of(1990, 10, 10));
        employee1.setStartJobDate(LocalDate.of(2015, 1, 1));

        Employee employee2 = new Employee();
        employee2.setName("Jane");
        employee2.setLastName("Smith");
        employee2.setBirthday(LocalDate.of(1985, 5, 15));
        employee2.setStartJobDate(LocalDate.of(2010, 2, 20));

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<EmployeeResponse> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).name());
        assertEquals("Jane Smith", result.get(1).name());
    }

    @Test
    void testDeactivateOrActivateEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setStatus("1");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        employeeService.deactivateOrActivateEmployeeById(1);

        assertEquals("0", employee.getStatus());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testDeactivateOrActivateEmployeeByIdInactiveToActive() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setStatus("0");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        employeeService.deactivateOrActivateEmployeeById(1);

        assertEquals("1", employee.getStatus());
        verify(employeeRepository, times(1)).save(employee);
    }
}
