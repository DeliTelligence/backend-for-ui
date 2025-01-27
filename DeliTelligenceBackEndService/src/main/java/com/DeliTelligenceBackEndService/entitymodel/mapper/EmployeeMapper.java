package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.entitymodeldto.EmployeeFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.EmployeeInputDto;
import com.DeliTelligenceBackEndService.service.EmployeeService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "hireDate", expression = "java(java.time.LocalDate.now())")
    Employee toEmployee(EmployeeInputDto employeeInputDto);

    EmployeeFetchDto toEmployeeFetchDto(Employee employee);

    default Employee map(UUID employeeId, @Context EmployeeService employeeService) {
        if (employeeId == null) {
            return null;
        }
        return employeeService.getEmployeeByIDReal(employeeId);
    }
}