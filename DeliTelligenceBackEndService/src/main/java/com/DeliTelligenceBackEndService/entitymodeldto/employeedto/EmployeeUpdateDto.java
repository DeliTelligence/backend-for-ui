package com.DeliTelligenceBackEndService.entitymodeldto.employeedto;

import com.DeliTelligenceBackEndService.enumformodel.EmployeeTitle;
import lombok.Data;

import java.util.UUID;

@Data
public class EmployeeUpdateDto {
    private UUID employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private EmployeeTitle employeeTitle;
    private String employeePassword;
}
