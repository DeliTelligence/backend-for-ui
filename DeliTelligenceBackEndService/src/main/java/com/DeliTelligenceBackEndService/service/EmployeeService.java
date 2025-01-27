package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.entitymodel.mapper.EmployeeMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.EmployeeRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.EmployeeFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.EmployeeInputDto;
import com.DeliTelligenceBackEndService.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeFetchDto> getAllEmployees() {
        List<EmployeeFetchDto> employeeFetchDtos = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            EmployeeFetchDto employeeFetchDto = employeeMapper.toEmployeeFetchDto(employee);
            employeeFetchDtos.add(employeeFetchDto);
        }
        return employeeFetchDtos;
    }

    public EmployeeFetchDto getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "ID", id));

        return employeeMapper.toEmployeeFetchDto(employee);

    }

    public Employee getEmployeeByIDReal(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "ID", id));
    }


    @Transactional
    public String createEmployee(EmployeeInputDto input) {
      Employee employee = employeeMapper.toEmployee(input);

       employeeRepository.save(employee);
       return "Done";

    }

    public String deleteEmployee(UUID id) {
         employeeRepository.deleteById(id);
         return "Employee deleted";

    }
}
