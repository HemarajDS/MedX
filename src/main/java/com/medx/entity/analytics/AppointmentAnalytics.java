package com.medx.entity.analytics;

import com.medx.entity.Clinic;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointment_analytics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private Integer totalAppointments;

    @Column(nullable = false)
    private Integer completedAppointments;

    @Column(nullable = false)
    private Integer cancelledAppointments;

    @Column(nullable = false)
    private Integer rescheduledAppointments;

    @Column(nullable = false)
    private Integer noShowAppointments;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double appointmentCompletionRate;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double cancelationRate;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double noShowRate;

    @Column(nullable = true)
    private Integer peakHour;

    @Column(nullable = true)
    private Integer averageWaitTimeMinutes;

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