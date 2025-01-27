package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.entitymodeldto.EmployeeFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.EmployeeInputDto;
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
    public List<EmployeeFetchDto> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @MutationMapping
    public String createEmployee(@Argument EmployeeInputDto input){
        return employeeService.createEmployee(input);
    }

    @MutationMapping
    public String deleteEmployee(@Argument("id") UUID id) {

       return employeeService.deleteEmployee(id);
    }


}
