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
public class AppointmentAnalyticsDTO {
    private Long id;
    private Long clinicId;
    private LocalDate appointmentDate;
    private Integer totalAppointments;
    private Integer completedAppointments;
    private Integer cancelledAppointments;
    private Integer rescheduledAppointments;
    private Integer noShowAppointments;
    private Double appointmentCompletionRate;
    private Double cancelationRate;
    private Double noShowRate;
    private Integer peakHour;
    private Integer averageWaitTimeMinutes;
}