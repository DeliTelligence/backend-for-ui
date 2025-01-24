package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.service.EmployeeService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    default Employee map(UUID employeeId, @Context EmployeeService employeeService) {
        if (employeeId == null) {
            return null;
        }
        return employeeService.getEmployeeById(employeeId);
    }
}