package com.medx.entity.analytics;

import com.medx.entity.Clinic;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medicine_analytics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @Column(nullable = false)
    private LocalDate analyticsDate;

    @Column(nullable = false)
    private Integer totalPrescriptions;

    @Column(nullable = false)
    private Integer activePrescriptions;

    @Column(nullable = false)
    private Integer completedPrescriptions;

    @Column(nullable = false)
    private Integer discontinuedPrescriptions;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double medicineComplianceRate;

    @Column(nullable = false)
    private Integer totalMedicinesTracked;

    @Column(nullable = false)
    private Integer uniqueMedicines;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String topPrescribedMedicines;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String medicinesByCategory;

    @Column(nullable = false)
    private Integer patientsWithSideEffects;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double averageMedicinesPerPrescription;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
}