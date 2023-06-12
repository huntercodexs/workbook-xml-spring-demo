package com.huntercodexs.workbook.job.demo.task.singlestep.processor;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WorkbookXmlProcessor {

    @Autowired
    WorkbookXmlValidationProcessor workbookXmlValidationProcessor;

    @Bean("singleStepProcess")
    public ItemProcessor<ProductDto, ProductDto> singleStepProcess() {

        log.info("WorkbookXmlProcessor say: (singleStepProcessor) is starting");

        return new CompositeItemProcessorBuilder<ProductDto, ProductDto>()
                .delegates(workbookXmlValidationProcessor)
                .build();
    }

}
