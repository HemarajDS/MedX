package com.medx.entity.analytics;

import com.medx.entity.Clinic;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "revenue_analytics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @Column(nullable = false)
    private LocalDate revenueDate;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double totalConsultationFees;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double totalProcedureFees;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double totalLabCharges;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double totalOtherCharges;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double totalRevenue;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double totalRefunds;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double totalOutstandingPayments;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double averageConsultationFee;

    @Column(nullable = false)
    private Integer paymentsDueWithin30Days;

    @Column(nullable = false)
    private Integer overduePayments;

    @Column(nullable = false, columnDefinition = "DECIMAL(12,2)")
    private Double netProfit;

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