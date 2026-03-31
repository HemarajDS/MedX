package com.medx.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientAnalyticsDTO {
    private Long id;
    private Long clinicId;
    private LocalDate analyticsDate;
    private Integer totalPatients;
    private Integer activePatients;
    private Integer inactivePatients;
    private Integer archivedPatients;
    private Integer newPatientsAdded;
    private Integer patientRetentionRate;
    private Integer malePatients;
    private Integer femalePatients;
    private Integer otherGenderPatients;
    private Double averagePatientAge;
    private Integer patientsByAgeGroup0_18;
    private Integer patientsByAgeGroup19_35;
    private Integer patientsByAgeGroup36_50;
    private Integer patientsByAgeGroup51_65;
    private Integer patientsByAgeGroup65Plus;
}