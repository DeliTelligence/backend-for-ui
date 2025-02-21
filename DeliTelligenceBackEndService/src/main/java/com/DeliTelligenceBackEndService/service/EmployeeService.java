package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.entitymodel.mapper.EmployeeMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.EmployeeRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeCreateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeUpdateDto;
import com.DeliTelligenceBackEndService.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeFetchDto login(String password) {
        Employee employee = getEmployeeByPassword(password);

        employee.setEmployeeLoggedIn(true);

        return employeeMapper.toEmployeeFetchDto(employee);

    }

    public Employee getEmployeeByPassword(String password) {
        return  employeeRepository.findByEmployeePassword(password).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Password", password));

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

    public List<Employee> getAllEmployeesReal(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByID(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "ID", id));
    }


    @Transactional
    public String createEmployee(EmployeeCreateDto input) {
      if (getEmployeeByPasswordHelp(input.getEmployeePassword()).isPresent()){
          return "Employee already exists";

      }
      Employee employee = employeeMapper.toEmployee(input);
      employeeRepository.save(employee);
      return "Done";
    }

    public String updateEmployee(EmployeeUpdateDto input) {
        Employee employeeToUpdate = employeeMapper.toEmployee(input, employeeRepository);
        employeeRepository.save(employeeToUpdate);
        return "Done";
    }

    public String deleteEmployee(UUID id) {
         employeeRepository.deleteById(id);
         return "Employee deleted";

    }

    public Optional<Employee> getEmployeeByPasswordHelp(String password) {
        return employeeRepository.findByEmployeePassword(password);
    }
}
