package com.huntercodexs.workbook.job.demo.step;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WorkbookXmlStepConfig {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step singleStep(
        @Qualifier("singleStepRead") ItemReader<ProductDto> reader,
        @Qualifier("singleStepProcess") ItemProcessor<ProductDto, ProductDto> processor,
        @Qualifier("singleStepWrite") ItemWriter<ProductDto> writer
    ) {

        log.info("WorkbookXmlStepConfig say: steps has been configured");
        log.info("WorkbookXmlStepConfig say: The steps will be running: " +
                reader.toString() +", "+
                processor.toString() + ", "+
                writer.toString());

        return stepBuilderFactory
            .get("singleStep")
            .<ProductDto, ProductDto>chunk(5_000)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }
}
