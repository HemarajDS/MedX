package com.medx.entity.analytics;

import com.medx.entity.Clinic;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient_analytics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @Column(nullable = false)
    private LocalDate analyticsDate;

    @Column(nullable = false)
    private Integer totalPatients;

    @Column(nullable = false)
    private Integer activePatients;

    @Column(nullable = false)
    private Integer inactivePatients;

    @Column(nullable = false)
    private Integer archivedPatients;

    @Column(nullable = false)
    private Integer newPatientsAdded;

    @Column(nullable = false)
    private Integer patientRetentionRate;

    @Column(nullable = false)
    private Integer malePatients;

    @Column(nullable = false)
    private Integer femalePatients;

    @Column(nullable = false)
    private Integer otherGenderPatients;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double averagePatientAge;

    @Column(nullable = false)
    private Integer patientsByAgeGroup0_18;

    @Column(nullable = false)
    private Integer patientsByAgeGroup19_35;

    @Column(nullable = false)
    private Integer patientsByAgeGroup36_50;

    @Column(nullable = false)
    private Integer patientsByAgeGroup51_65;

    @Column(nullable = false)
    private Integer patientsByAgeGroup65Plus;

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