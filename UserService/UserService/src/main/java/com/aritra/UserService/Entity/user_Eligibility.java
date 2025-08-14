package com.aritra.UserService.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class user_Eligibility {
  
    private Long id;                      // Primary key (maps to ELIGIBILITY_ID or similar)
    private Long userId;                  // Foreign key to T_FLT_USERS
    private String eligibilityTypeCode;   // Foreign key to T_FLT_CAR_ELIGIBILITY_TYPES
    private LocalDateTime effectiveFrom;  // Start date of eligibility
    private LocalDateTime effectiveTo;    // End date of eligibility
    private Boolean active;               // Eligibility active flag
    private String createdBy;             // User who created the record
    private LocalDateTime createdDate;    // Creation timestamp
    private String updatedBy;             // Last updated by
    private LocalDateTime updatedDate;    // Last update timestamp

    // Optional — if you have relationships
    // private User user;
    // private EligibilityType eligibilityType;

    // Getters and Setters
    
}
