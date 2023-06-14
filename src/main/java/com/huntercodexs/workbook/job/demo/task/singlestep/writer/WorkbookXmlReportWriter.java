package com.huntercodexs.workbook.job.demo.task.singlestep.writer;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import com.huntercodexs.workbook.job.demo.workbook.WorkbookHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        List<String> cols = new ArrayList<>();
        cols.add("id");
        cols.add("name");
        cols.add("description");
        cols.add("price");

        ArrayList<List<?>> rows = new ArrayList<>();
        productDto.forEach(item -> {
            ArrayList<String> list = new ArrayList<>();
            list.add(String.valueOf(item.getId()));
            list.add(item.getName());
            list.add(item.getDescription());
            list.add(item.getPrice());
            rows.add(list);
        });

        /*CREATE WORKBOOK*/
        workbookHandler.create();

        /*SHEET 1*/
        workbookHandler.createSheet("SAMPLE 1", cols, rows);
        workbookHandler.toHeader().cellBorder("full", "solid");
        workbookHandler.toHeader().backColor("orange");
        workbookHandler.toHeader().fontColor("white");
        workbookHandler.toHeader().vAlign("center");
        workbookHandler.toHeader().hAlign("middle");
        workbookHandler.toHeader().weight("bold");
        workbookHandler.toHeader().fontSize((short) 14);
        workbookHandler.toHeader().cellWidth(256);
        workbookHandler.toHeader().cellHeight(1200);
        workbookHandler.createHeader();

        workbookHandler.toBody().cellBorder("full", "solid");
        workbookHandler.toBody().backColor("white");
        workbookHandler.toBody().fontColor("blue");
        workbookHandler.toBody().vAlign("left");
        workbookHandler.toBody().hAlign("middle");
        workbookHandler.toBody().weight("normal");
        workbookHandler.toBody().fontSize((short) 11);
        workbookHandler.toBody().cellWidth(256);
        workbookHandler.toBody().cellHeight(500);
        workbookHandler.createBody();

        /*SHEET 2*/
        workbookHandler.createSheet("SAMPLE 2", cols, rows);
        workbookHandler.createHeader();
        workbookHandler.createBody();

        /*SHEET 3*/
        workbookHandler.reset();
        workbookHandler.createSheet("SAMPLE 3", cols, rows);
        workbookHandler.createHeader();
        workbookHandler.createBody();

        /*SHEET 4*/
        workbookHandler.reset();
        workbookHandler.createSheet("SAMPLE 4", cols, rows);
        workbookHandler.toHeader().cellBorder("full", "solid");
        workbookHandler.toHeader().backColor("green");
        workbookHandler.toHeader().fontColor("yellow");
        workbookHandler.toHeader().vAlign("center");
        workbookHandler.toHeader().hAlign("middle");
        workbookHandler.toHeader().weight("bold");
        workbookHandler.toHeader().fontSize((short) 14);
        workbookHandler.toHeader().cellWidth(256);
        workbookHandler.toHeader().cellHeight(1200);
        workbookHandler.createHeader();

        workbookHandler.toBody().cellBorder("full", "solid");
        workbookHandler.toBody().backColor("white");
        workbookHandler.toBody().fontColor("black");
        workbookHandler.toBody().vAlign("left");
        workbookHandler.toBody().hAlign("middle");
        workbookHandler.toBody().weight("normal");
        workbookHandler.toBody().fontSize((short) 11);
        workbookHandler.toBody().cellWidth(256);
        workbookHandler.toBody().cellHeight(500);
        workbookHandler.createBody();

        workbookHandler.save("WorkbookXmlReport-"+dateTimeFormat+".xlsx");
        ByteArrayDataSource workbookDataSource = workbookHandler.bytes();

    }
}
