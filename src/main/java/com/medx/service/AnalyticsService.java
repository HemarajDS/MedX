package com.medx.service.analytics;

import com.medx.dto.analytics.*;
import com.medx.entity.analytics.*;
import com.medx.repository.analytics.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyticsService {

    private final AppointmentAnalyticsRepository appointmentAnalyticsRepository;
    private final PatientAnalyticsRepository patientAnalyticsRepository;
    private final MedicineAnalyticsRepository medicineAnalyticsRepository;
    private final RevenueAnalyticsRepository revenueAnalyticsRepository;
    private final DoctorPerformanceAnalyticsRepository doctorPerformanceAnalyticsRepository;

    public AppointmentAnalyticsDTO getAppointmentAnalytics(Long clinicId, LocalDate date) {
        return appointmentAnalyticsRepository.findByClinicIdAndAppointmentDate(clinicId, date)
                .map(this::mapToAppointmentDTO)
                .orElse(null);
    }

    public List<AppointmentAnalyticsDTO> getAppointmentAnalyticsRange(Long clinicId, LocalDate startDate, LocalDate endDate) {
        return appointmentAnalyticsRepository.findByClinicIdAndAppointmentDateBetween(clinicId, startDate, endDate)
                .stream()
                .map(this::mapToAppointmentDTO)
                .collect(Collectors.toList());
    }

    public PatientAnalyticsDTO getPatientAnalytics(Long clinicId, LocalDate date) {
        return patientAnalyticsRepository.findByClinicIdAndAnalyticsDate(clinicId, date)
                .map(this::mapToPatientDTO)
                .orElse(null);
    }

    public MedicineAnalyticsDTO getMedicineAnalytics(Long clinicId, LocalDate date) {
        return medicineAnalyticsRepository.findByClinicIdAndAnalyticsDate(clinicId, date)
                .map(this::mapToMedicineDTO)
                .orElse(null);
    }

    public RevenueAnalyticsDTO getRevenueAnalytics(Long clinicId, LocalDate date) {
        return revenueAnalyticsRepository.findByClinicIdAndRevenueDate(clinicId, date)
                .map(this::mapToRevenueDTO)
                .orElse(null);
    }

    public Double getTotalRevenueInRange(Long clinicId, LocalDate startDate, LocalDate endDate) {
        return revenueAnalyticsRepository.getTotalRevenueInRange(clinicId, startDate, endDate);
    }

    public List<DoctorPerformanceDTO> getTopDoctorsByRevenue(Long clinicId, LocalDate date) {
        return doctorPerformanceAnalyticsRepository.findTopDoctorsByRevenue(clinicId, date)
                .stream()
                .map(this::mapToDoctorPerformanceDTO)
                .collect(Collectors.toList());
    }

    public DashboardSummaryDTO getDashboardSummary(Long clinicId) {
        LocalDate today = LocalDate.now();
        LocalDate monthStart = today.withDayOfMonth(1);

        return DashboardSummaryDTO.builder()
                .totalPatients(0)
                .todayAppointments(0)
                .completedAppointments(0)
                .todayRevenue(0.0)
                .monthlyRevenue(getTotalRevenueInRange(clinicId, monthStart, today))
                .activeDoctors(0)
                .appointmentCompletionRate(0.0)
                .patientRetentionRate(0.0)
                .newPatientsThisMonth(0)
                .pendingTasks(0)
                .averagePatientSatisfaction(0.0)
                .build();
    }

    private AppointmentAnalyticsDTO mapToAppointmentDTO(AppointmentAnalytics entity) {
        return AppointmentAnalyticsDTO.builder()
                .id(entity.getId())
                .clinicId(entity.getClinic().getId())
                .appointmentDate(entity.getAppointmentDate())
                .totalAppointments(entity.getTotalAppointments())
                .completedAppointments(entity.getCompletedAppointments())
                .cancelledAppointments(entity.getCancelledAppointments())
                .rescheduledAppointments(entity.getRescheduledAppointments())
                .noShowAppointments(entity.getNoShowAppointments())
                .appointmentCompletionRate(entity.getAppointmentCompletionRate())
                .cancelationRate(entity.getCancelationRate())
                .noShowRate(entity.getNoShowRate())
                .peakHour(entity.getPeakHour())
                .averageWaitTimeMinutes(entity.getAverageWaitTimeMinutes())
                .build();
    }

    private PatientAnalyticsDTO mapToPatientDTO(PatientAnalytics entity) {
        return PatientAnalyticsDTO.builder()
                .id(entity.getId())
                .clinicId(entity.getClinic().getId())
                .analyticsDate(entity.getAnalyticsDate())
                .totalPatients(entity.getTotalPatients())
                .activePatients(entity.getActivePatients())
                .inactivePatients(entity.getInactivePatients())
                .archivedPatients(entity.getArchivedPatients())
                .newPatientsAdded(entity.getNewPatientsAdded())
                .patientRetentionRate(entity.getPatientRetentionRate())
                .malePatients(entity.getMalePatients())
                .femalePatients(entity.getFemalePatients())
                .otherGenderPatients(entity.getOtherGenderPatients())
                .averagePatientAge(entity.getAveragePatientAge())
                .build();
    }

    private MedicineAnalyticsDTO mapToMedicineDTO(MedicineAnalytics entity) {
        return MedicineAnalyticsDTO.builder()
                .id(entity.getId())
                .clinicId(entity.getClinic().getId())
                .analyticsDate(entity.getAnalyticsDate())
                .totalPrescriptions(entity.getTotalPrescriptions())
                .activePrescriptions(entity.getActivePrescriptions())
                .completedPrescriptions(entity.getCompletedPrescriptions())
                .discontinuedPrescriptions(entity.getDiscontinuedPrescriptions())
                .medicineComplianceRate(entity.getMedicineComplianceRate())
                .uniqueMedicines(entity.getUniqueMedicines())
                .topPrescribedMedicines(entity.getTopPrescribedMedicines())
                .build();
    }

    private RevenueAnalyticsDTO mapToRevenueDTO(RevenueAnalytics entity) {
        return RevenueAnalyticsDTO.builder()
                .id(entity.getId())
                .clinicId(entity.getClinic().getId())
                .revenueDate(entity.getRevenueDate())
                .totalConsultationFees(entity.getTotalConsultationFees())
                .totalProcedureFees(entity.getTotalProcedureFees())
                .totalLabCharges(entity.getTotalLabCharges())
                .totalRevenue(entity.getTotalRevenue())
                .totalRefunds(entity.getTotalRefunds())
                .totalOutstandingPayments(entity.getTotalOutstandingPayments())
                .netProfit(entity.getNetProfit())
                .build();
    }

    private DoctorPerformanceDTO mapToDoctorPerformanceDTO(DoctorPerformanceAnalytics entity) {
        return DoctorPerformanceDTO.builder()
                .id(entity.getId())
                .doctorId(entity.getDoctor().getId())
                .doctorName(entity.getDoctor().getFullName())
                .totalAppointments(entity.getTotalAppointments())
                .completedAppointments(entity.getCompletedAppointments())
                .cancelledAppointments(entity.getCancelledAppointments())
                .noShowAppointments(entity.getNoShowAppointments())
                .appointmentCompletionRate(entity.getAppointmentCompletionRate())
                .patientSatisfactionScore(entity.getPatientSatisfactionScore())
                .totalFeesGenerated(entity.getTotalFeesGenerated())
                .newPatients(entity.getNewPatients())
                .build();
    }
}