package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeCreateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.employeedto.EmployeeUpdateDto;
import com.DeliTelligenceBackEndService.service.EmployeeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @QueryMapping
    public List<EmployeeFetchDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @QueryMapping
    public EmployeeFetchDto employeeLogin(@Argument("password") String password) {
        return employeeService.login(password);
    }

    @MutationMapping
    public String createEmployee(@Argument EmployeeCreateDto input){
        return employeeService.createEmployee(input);
    }

    @MutationMapping
    public String editEmployee(@Argument EmployeeUpdateDto input){
        return employeeService.updateEmployee(input);
    }

    @MutationMapping
    public String deleteEmployee(@Argument("id") UUID id) {
       return employeeService.deleteEmployee(id);
    }


}
