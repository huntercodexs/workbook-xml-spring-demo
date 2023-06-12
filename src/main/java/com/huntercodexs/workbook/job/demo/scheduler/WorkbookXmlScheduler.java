package com.huntercodexs.workbook.job.demo.scheduler;

import com.huntercodexs.workbook.job.demo.config.workbookXmlQuartzConfig;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WorkbookXmlScheduler {

    @Value("${job.workbook-xml.enabled:true}")
    boolean jobEnabled;

    @Value("${job.workbook-xml.hour:0}")
    int jobHour;

    @Value("${job.workbook-xml.minute:0}")
    int jobMinute;

    @Bean
    public JobDetail workbookXmlJobDetail() {

        if (!jobEnabled) {
            log.warn("Schedule say: (JobDetail) workbookXmlJobDetail is disabled by properties");
            return null;
        }

        return JobBuilder
                .newJob(workbookXmlQuartzConfig.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger WorkbookXmlTrigger() {

        if (!jobEnabled) {
            log.warn("Schedule say: (Trigger) workbookXmlJobDetail is disabled by properties");
            return null;
        }

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
            .dailyAtHourAndMinute(jobHour, jobMinute);

        log.info("Schedule say: (Trigger) workbookXmlJobDetail is starting");
        log.info("Schedule say: Cron Settings: " + cronScheduleBuilder);

        return TriggerBuilder
            .newTrigger()
            .forJob(workbookXmlJobDetail())
            .withSchedule(cronScheduleBuilder)
            .build();
    }
}
