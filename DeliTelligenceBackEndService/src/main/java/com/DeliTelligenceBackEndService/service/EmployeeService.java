package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.entitymodel.repository.EmployeeRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.EmployeeInputDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Transactional
    public Employee createEmployee(EmployeeInputDto input) {
        Employee employee = new Employee();
        employee.setEmployeeFirstName(input.getEmployeeFirstName());
        employee.setEmployeeLastName(input.getEmployeeLastName());
        employee.setEmployeeTitle(input.getEmployeeTitle());
        employee.setEmployeeLoggedIn(false);
        employee.setHireDate(LocalDate.now());

    return employeeRepository.save(employee);

    }

    public String deleteEmployee(UUID id) {
         employeeRepository.deleteById(id);
         return "Employee deleted";

    }
}
