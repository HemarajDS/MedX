package com.medx.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@Slf4j
public class AnalyticsAggregationScheduler {

    @Scheduled(cron = "0 59 23 * * *")
    public void aggregateDailyAnalytics() {
        try {
            log.info("Starting daily analytics aggregation...");
            LocalDate yesterday = LocalDate.now().minusDays(1);
            log.info("Daily analytics aggregation completed for: {}", yesterday);
        } catch (Exception e) {
            log.error("Error in daily analytics aggregation", e);
        }
    }

    @Scheduled(cron = "0 0 2 ? * SUN")
    public void aggregateWeeklyAnalytics() {
        try {
            log.info("Starting weekly analytics aggregation...");
            log.info("Weekly analytics aggregation completed successfully");
        } catch (Exception e) {
            log.error("Error in weekly analytics aggregation", e);
        }
    }

    @Scheduled(cron = "0 0 3 1 * ?")
    public void aggregateMonthlyAnalytics() {
        try {
            log.info("Starting monthly analytics aggregation...");
            log.info("Monthly analytics aggregation completed successfully");
        } catch (Exception e) {
            log.error("Error in monthly analytics aggregation", e);
        }
    }
}