package com.medx.repository.analytics;

import com.medx.entity.analytics.RevenueAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RevenueAnalyticsRepository extends JpaRepository<RevenueAnalytics, Long> {
    Optional<RevenueAnalytics> findByClinicIdAndRevenueDate(Long clinicId, LocalDate revenueDate);
    List<RevenueAnalytics> findByClinicIdAndRevenueDateBetween(Long clinicId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(ra.totalRevenue) FROM RevenueAnalytics ra WHERE ra.clinic.id = :clinicId AND ra.revenueDate BETWEEN :startDate AND :endDate")
    Double getTotalRevenueInRange(@Param("clinicId") Long clinicId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(ra.netProfit) FROM RevenueAnalytics ra WHERE ra.clinic.id = :clinicId AND ra.revenueDate BETWEEN :startDate AND :endDate")
    Double getTotalNetProfit(@Param("clinicId") Long clinicId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}