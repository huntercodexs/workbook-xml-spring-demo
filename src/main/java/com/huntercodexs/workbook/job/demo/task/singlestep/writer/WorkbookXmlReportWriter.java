package com.huntercodexs.workbook.job.demo.task.singlestep.writer;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import com.huntercodexs.workbook.job.demo.workbook.WorkbookHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
public class WorkbookXmlReportWriter implements ItemWriter<ProductDto> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");

    @Autowired
    WorkbookHandler workbookHandler;


    @Override
    public void write(List<? extends ProductDto> productDto) throws IOException {

        /*Prevent send mail when not exists items*/
        if (productDto.size() == 0) {
            log.info("WorkbookXmlReportWriter say: (write) not exists items to send mail");
            return;
        }

        LocalDateTime dateTimeNow = LocalDateTime.now();
        String dateTimeFormat = dateTimeNow.format(FORMATTER);

        XSSFWorkbook workbook = workbookHandler.create(productDto);
        ByteArrayDataSource workbookDataSource = workbookHandler.bytes(workbook);
        workbookHandler.save("WorkbookXmlReport-"+dateTimeFormat+".xlsx", workbook);

    }
}
