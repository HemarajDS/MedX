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
public class RevenueAnalyticsDTO {
    private Long id;
    private Long clinicId;
    private LocalDate revenueDate;
    private Double totalConsultationFees;
    private Double totalProcedureFees;
    private Double totalLabCharges;
    private Double totalOtherCharges;
    private Double totalRevenue;
    private Double totalRefunds;
    private Double totalOutstandingPayments;
    private Double averageConsultationFee;
    private Integer paymentsDueWithin30Days;
    private Integer overduePayments;
    private Double netProfit;
}