package com.DeliTelligenceBackEndService.entitymodeldto;

import com.DeliTelligenceBackEndService.enumformodel.EmployeeTitle;
import lombok.Data;

@Data
public class EmployeeInputDto {
    private String employeeFirstName;
    private String employeeLastName;
    private EmployeeTitle employeeTitle;
    private String employeePassword;
}
