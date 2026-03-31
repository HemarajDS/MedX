package com.medx.controller;

import com.medx.dto.analytics.*;
import com.medx.service.analytics.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final AnalyticsService analyticsService;

    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLINIC_MANAGER', 'DOCTOR')")
    public ResponseEntity<DashboardSummaryDTO> getDashboardSummary(@RequestParam Long clinicId) {
        DashboardSummaryDTO summary = analyticsService.getDashboardSummary(clinicId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/appointments/analytics")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLINIC_MANAGER')")
    public ResponseEntity<?> getAppointmentAnalytics(
            @RequestParam Long clinicId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        if (date != null) {
            return ResponseEntity.ok(analyticsService.getAppointmentAnalytics(clinicId, date));
        }
        if (startDate != null && endDate != null) {
            return ResponseEntity.ok(analyticsService.getAppointmentAnalyticsRange(clinicId, startDate, endDate));
        }
        return ResponseEntity.badRequest().body("Provide either 'date' or 'startDate' and 'endDate'");
    }

    @GetMapping("/patients/demographics")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLINIC_MANAGER')")
    public ResponseEntity<?> getPatientDemographics(
            @RequestParam Long clinicId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(analyticsService.getPatientAnalytics(clinicId, date != null ? date : LocalDate.now()));
    }

    @GetMapping("/revenue/total")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLINIC_MANAGER')")
    public ResponseEntity<Double> getTotalRevenue(
            @RequestParam Long clinicId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Double totalRevenue = analyticsService.getTotalRevenueInRange(clinicId, startDate, endDate);
        return ResponseEntity.ok(totalRevenue != null ? totalRevenue : 0.0);
    }

    @GetMapping("/doctors/top-by-revenue")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLINIC_MANAGER')")
    public ResponseEntity<List<DoctorPerformanceDTO>> getTopDoctorsByRevenue(
            @RequestParam Long clinicId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(analyticsService.getTopDoctorsByRevenue(clinicId, date != null ? date : LocalDate.now()));
    }
}