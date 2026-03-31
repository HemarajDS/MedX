package com.medx.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDTO {
    private Integer totalPatients;
    private Integer todayAppointments;
    private Integer completedAppointments;
    private Double todayRevenue;
    private Double monthlyRevenue;
    private Integer activeDoctors;
    private Double appointmentCompletionRate;
    private Double patientRetentionRate;
    private Integer newPatientsThisMonth;
    private Integer pendingTasks;
    private Double averagePatientSatisfaction;
}