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
public class MedicineAnalyticsDTO {
    private Long id;
    private Long clinicId;
    private LocalDate analyticsDate;
    private Integer totalPrescriptions;
    private Integer activePrescriptions;
    private Integer completedPrescriptions;
    private Integer discontinuedPrescriptions;
    private Double medicineComplianceRate;
    private Integer totalMedicinesTracked;
    private Integer uniqueMedicines;
    private String topPrescribedMedicines;
    private String medicinesByCategory;
    private Integer patientsWithSideEffects;
    private Double averageMedicinesPerPrescription;
}