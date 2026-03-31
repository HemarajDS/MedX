package com.medx.repository.analytics;

import com.medx.entity.analytics.AppointmentAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentAnalyticsRepository extends JpaRepository<AppointmentAnalytics, Long> {
    Optional<AppointmentAnalytics> findByClinicIdAndAppointmentDate(Long clinicId, LocalDate appointmentDate);
    List<AppointmentAnalytics> findByClinicIdAndAppointmentDateBetween(Long clinicId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT aa FROM AppointmentAnalytics aa WHERE aa.clinic.id = :clinicId ORDER BY aa.appointmentDate DESC LIMIT 1")
    Optional<AppointmentAnalytics> findLatestAnalytics(@Param("clinicId") Long clinicId);

    @Query("SELECT AVG(aa.appointmentCompletionRate) FROM AppointmentAnalytics aa WHERE aa.clinic.id = :clinicId AND aa.appointmentDate BETWEEN :startDate AND :endDate")
    Double getAverageCompletionRate(@Param("clinicId") Long clinicId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(aa.totalAppointments) FROM AppointmentAnalytics aa WHERE aa.clinic.id = :clinicId AND aa.appointmentDate BETWEEN :startDate AND :endDate")
    Integer getTotalAppointmentsInRange(@Param("clinicId") Long clinicId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}