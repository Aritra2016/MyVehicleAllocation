package com.aritra.UserService.Entity;

import java.time.LocalDateTime;

import com.aritra.UserService.Domain.UserRole;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    // Use @GeneratedValue for auto-incrementing primary key
    private Long id;
    private String employeeNo;

    @Column(unique=true)
    private String firstName;
    @Column(unique=true)
    private String lastname;
    
    private String email;

    @Column(unique=true)
    private String phone;


    private String country;
    private String legalEntity;           // For multinational organizations
    private String department;            // Optional: User's department
    private String role;                  // User role (linked to UserRole)
    private Boolean active;               // User active status
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;
    private String eligibilityType;       // May link to UserEligibility
    private String bu;                    // Business unit, if needed
    private String adId;  
    
    private UserRole userrole;
    
    // For authentication, if using Active Directory

    // Optionally, you may want a list/set of eligibility or roles:
    // private Set<UserEligibility> eligibilities;
    // private Set<UserRole> roles;

    // Getters and Setters

}
