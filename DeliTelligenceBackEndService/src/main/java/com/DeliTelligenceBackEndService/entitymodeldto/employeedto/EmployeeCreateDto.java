package com.DeliTelligenceBackEndService.entitymodeldto.employeedto;

import com.DeliTelligenceBackEndService.enumformodel.EmployeeTitle;
import lombok.Data;

@Data
public class EmployeeCreateDto {
    private String employeeFirstName;
    private String employeeLastName;
    private EmployeeTitle employeeTitle;
    private String employeePassword;
}
