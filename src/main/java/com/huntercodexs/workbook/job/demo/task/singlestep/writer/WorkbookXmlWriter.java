package com.huntercodexs.workbook.job.demo.task.singlestep.writer;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WorkbookXmlWriter {

    @Autowired
    WorkbookXmlReportWriter workbookXmlReportWriter;

    @Bean("singleStepWrite")
    public ItemWriter<ProductDto> singleStepWrite() {

        log.info("WorkbookXmlWriter say: (singleStepWriter) is starting");

        return new CompositeItemWriterBuilder<ProductDto>()
            .delegates(workbookXmlReportWriter).build();
    }
}
