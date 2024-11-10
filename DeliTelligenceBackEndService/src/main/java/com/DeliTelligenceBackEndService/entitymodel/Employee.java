package com.DeliTelligenceBackEndService.entitymodel;

import com.DeliTelligenceBackEndService.enumformodel.EmployeeTitle;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "EMPLOYEE_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID) // Change from SqlTypes.VARCHAR to SqlTypes.UUID
    private UUID id;

    @Column(name = "employee_first_name", nullable = false, length = 50)
    private String employeeFirstName;

    @Column(name = "employee_last_name", nullable = false, length = 50)
    private String employeeLastName;

    @Column(name = "employee_logged_in", nullable = false)
    private Boolean employeeLoggedIn = false;

    @Column(name = "employee_title", nullable = false, length = 50)
    private EmployeeTitle employeeTitle;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("employee-Sale")
    private List<DeliSale> sales;

}