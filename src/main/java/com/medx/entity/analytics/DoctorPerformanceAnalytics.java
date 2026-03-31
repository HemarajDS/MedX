package com.medx.entity.analytics;

import com.medx.entity.Clinic;
import com.medx.entity.Doctor;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "doctor_performance_analytics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPerformanceAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDate analyticsDate;

    @Column(nullable = false)
    private Integer totalAppointments;

    @Column(nullable = false)
    private Integer completedAppointments;

    @Column(nullable = false)
    private Integer cancelledAppointments;

    @Column(nullable = false)
    private Integer noShowAppointments;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double appointmentCompletionRate;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double patientSatisfactionScore;

    @Column(nullable = false)
    private Integer patientReviews;

    @Column(nullable = false)
    private Integer prescriptionsIssued;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double totalFeesGenerated;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double averageConsultationTime;

    @Column(nullable = false)
    private Integer newPatients;

    @Column(nullable = false)
    private Integer reportedComplaints;

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