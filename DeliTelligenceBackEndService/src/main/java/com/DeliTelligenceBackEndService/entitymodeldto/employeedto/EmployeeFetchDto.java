package com.DeliTelligenceBackEndService.entitymodeldto.employeedto;

import com.DeliTelligenceBackEndService.enumformodel.EmployeeTitle;
import lombok.Data;

import java.util.UUID;

@Data
public class EmployeeFetchDto {
    private UUID id;
    private String employeeFirstName;
    private String employeeLastName;
    private String hireDate;
    private EmployeeTitle employeeTitle;
    private boolean employeeLoggedIn;
    private String employeePassword;
}

