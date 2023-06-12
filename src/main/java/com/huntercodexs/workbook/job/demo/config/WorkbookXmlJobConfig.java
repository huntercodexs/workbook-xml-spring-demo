package com.huntercodexs.workbook.job.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class WorkbookXmlJobConfig {

    @Value("${job.workbook-xml.enabled:true}")
    boolean jobEnabled;

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job workbookXmlJob(
        @Qualifier("singleStep") Step step1
    ) {

        if (!jobEnabled) {
            log.warn("Job Config say: workbookXmlJob is disabled by properties");
            return null;
        }

        log.info("Job Config say: workbookXmlJob is starting");
        log.info("Job Config say: The steps will be running: " + step1.toString());

        return jobBuilderFactory
                .get("workbookXmlJob")
                .start(step1)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
