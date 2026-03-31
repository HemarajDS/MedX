package com.medx.repository.analytics;

import com.medx.entity.analytics.PatientAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientAnalyticsRepository extends JpaRepository<PatientAnalytics, Long> {
    Optional<PatientAnalytics> findByClinicIdAndAnalyticsDate(Long clinicId, LocalDate analyticsDate);
    List<PatientAnalytics> findByClinicIdAndAnalyticsDateBetween(Long clinicId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(pa.newPatientsAdded) FROM PatientAnalytics pa WHERE pa.clinic.id = :clinicId AND pa.analyticsDate BETWEEN :startDate AND :endDate")
    Integer getTotalNewPatients(@Param("clinicId") Long clinicId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}