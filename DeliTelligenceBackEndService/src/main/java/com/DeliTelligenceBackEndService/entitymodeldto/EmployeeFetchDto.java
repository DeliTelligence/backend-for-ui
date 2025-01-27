package com.DeliTelligenceBackEndService.entitymodeldto;

import com.DeliTelligenceBackEndService.entitymodel.Employee;
import lombok.Data;

@Data
public class EmployeeFetchDto {
    private String employeeFirstName;
    private String employeeLastName;
    private String hireDate;
    private Employee employeeTitle;
    private boolean employeeLoggedIn;
}
