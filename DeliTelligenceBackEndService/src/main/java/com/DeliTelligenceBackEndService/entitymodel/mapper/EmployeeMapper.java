package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.helper.EditCustomMapping;
import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.entitymodel.repository.EmployeeRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeCreateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeUpdateDto;
import com.DeliTelligenceBackEndService.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    // Employee Creation Mapper
    @Mapping(target = "hireDate", expression = "java(java.time.LocalDate.now())")
    Employee toEmployee(EmployeeCreateDto employeeInputDto);

    EmployeeFetchDto toEmployeeFetchDto(Employee employee);

    // Update logic for the Employee
    @BeforeMapping
    default void loadEmployee(EmployeeUpdateDto employeeUpdateDto, @MappingTarget Employee employee, @Context EmployeeRepository employeeRepository) {
        Employee loadedEmployee = employeeRepository.findById(employeeUpdateDto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found for this id :: " + employeeUpdateDto.getEmployeeId()));

        employee.setEmployeeFirstName(loadedEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(loadedEmployee.getEmployeeLastName());
        employee.setEmployeeTitle(loadedEmployee.getEmployeeTitle());
        employee.setEmployeePassword(loadedEmployee.getEmployeePassword());
        employee.setEmployeeLoggedIn(loadedEmployee.getEmployeeLoggedIn());
        employee.setHireDate(loadedEmployee.getHireDate());
        employee.setSales(loadedEmployee.getSales());

    }

    @Mapping(target = "id", source = "employeeId")
    @Mapping(target = "employeeFirstName", conditionExpression = "java(isDifferentOrNull(employee.getEmployeeFirstName(), employeeUpdateDto.getEmployeeFirstName()))", source = "employeeFirstName")
    @Mapping(target = "employeeLastName", conditionExpression = "java(isDifferentOrNull(employee.getEmployeeLastName(), employeeUpdateDto.getEmployeeLastName()))", source = "employeeLastName")
    @Mapping(target = "employeeTitle", conditionExpression = "java(isDifferentOrNull(employee.getEmployeeTitle(), employeeUpdateDto.getEmployeeTitle()))", source = "employeeTitle")
    @Mapping(target = "employeePassword", conditionExpression = "java(isDifferentOrNull(employee.getEmployeePassword(), employeeUpdateDto.getEmployeePassword()))", source = "employeePassword")
    Employee toEmployee(EmployeeUpdateDto employeeUpdateDto, @Context EmployeeRepository employeeRepository);

        default boolean isDifferentOrNull(Object entityValue, Object dtoValue) {
            return EditCustomMapping.isDifferentOrNull(entityValue, dtoValue);
    }

// mapping for the deli sale mapper
    default Employee map(UUID employeeId, @Context EmployeeService employeeService) {
        if (employeeId == null) {
            return null;
        }
        return employeeService.getEmployeeByID(employeeId);
    }
}