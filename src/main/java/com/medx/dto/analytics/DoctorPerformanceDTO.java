package com.medx.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPerformanceDTO {
    private Long id;
    private Long doctorId;
    private String doctorName;
    private Integer totalAppointments;
    private Integer completedAppointments;
    private Integer cancelledAppointments;
    private Integer noShowAppointments;
    private Double appointmentCompletionRate;
    private Double patientSatisfactionScore;
    private Integer patientReviews;
    private Integer prescriptionsIssued;
    private Double totalFeesGenerated;
    private Double averageConsultationTime;
    private Integer newPatients;
    private Integer reportedComplaints;
}