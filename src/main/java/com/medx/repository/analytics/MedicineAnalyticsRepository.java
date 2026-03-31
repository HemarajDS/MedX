package com.medx.repository.analytics;

import com.medx.entity.analytics.MedicineAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineAnalyticsRepository extends JpaRepository<MedicineAnalytics, Long> {
    Optional<MedicineAnalytics> findByClinicIdAndAnalyticsDate(Long clinicId, LocalDate analyticsDate);
    List<MedicineAnalytics> findByClinicIdAndAnalyticsDateBetween(Long clinicId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(ma.totalPrescriptions) FROM MedicineAnalytics ma WHERE ma.clinic.id = :clinicId AND ma.analyticsDate BETWEEN :startDate AND :endDate")
    Integer getTotalPrescriptions(@Param("clinicId") Long clinicId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}