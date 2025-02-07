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
@Table(name = "TBL_EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "EMPLOYEE_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID) // Change from SqlTypes.VARCHAR to SqlTypes.UUID
    private UUID id;

    @Column(name = "EMPLOYEE_FIRST_NAME", nullable = false, length = 50)
    private String employeeFirstName;

    @Column(name = "EMPLOYEE_LAST_NAME", nullable = false, length = 50)
    private String employeeLastName;

    @Column(name = "EMPLOYEE_LOGGED_IN", nullable = false)
    private Boolean employeeLoggedIn = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "EMPLOYEE_TITLE", nullable = false, length = 50)
    private EmployeeTitle employeeTitle;

    @Column(name = "HIRE_DATE", nullable = false)
    private LocalDate hireDate;

    @Column(name= "EMPLOYEE_PASSWORD", nullable = false)
    private String employeePassword;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("employee-Sale")
    private List<DeliSale> sales;

}