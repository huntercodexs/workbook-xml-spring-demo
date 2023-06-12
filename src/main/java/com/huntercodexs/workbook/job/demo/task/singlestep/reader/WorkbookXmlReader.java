package com.huntercodexs.workbook.job.demo.task.singlestep.reader;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import com.huntercodexs.workbook.job.demo.mapper.WorkbookXmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class WorkbookXmlReader {

    @Bean
    public JdbcCursorItemReader<ProductDto> singleStepRead(
            @Qualifier("springDataSource") DataSource dataSource
    ) {

        log.info("WorkbookXmlReader say: (singleStepRead) is starting");

        String sql =
        """
        SELECT * FROM PRODUCTS
        """;

        return new JdbcCursorItemReaderBuilder<ProductDto>()
            .name("singleStepReadReader")
            .sql(sql)
            .dataSource(dataSource)
            .rowMapper(new WorkbookXmlMapper())
            .verifyCursorPosition(false)
            .build();

    }
}






