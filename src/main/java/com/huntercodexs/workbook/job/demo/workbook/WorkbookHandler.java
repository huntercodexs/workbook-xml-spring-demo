package com.huntercodexs.workbook.job.demo.workbook;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WorkbookHandler extends WorkbookStyles {

    private List<?> cols = new ArrayList<>();
    private ArrayList<List<?>> rows = new ArrayList<>();

    public void create() {
        this.workbook = new XSSFWorkbook();
    }

    public void createSheet(String name, List<String> cols, ArrayList<List<?>> rows) {
        this.sheet = this.workbook.createSheet(name);
        this.cols = cols;
        this.rows = rows;
    }

    public void createHeader() {
        this.header();
    }

    public void createBody() {
        this.body();
    }

    private void header() {

        if (this.cols == null) {
            log.error("Workbook say: header columns is null, is this correct ?");
            return;
        }

        int rowCounter = 0;
        int colCounter = 0;

        Row row = this.sheet.createRow(rowCounter++);

        for (Object column : this.cols) {

            if (column == null) {
                column = "";
                log.warn("Workbook say: header column is null, is this correct ?");
            }

            Cell cell = row.createCell(colCounter++);
            cell.setCellStyle(this.cellHeaderStyle);
            cell.setCellValue(column.toString().toUpperCase());

            if (this.cellHeaderHeight > 0) {
                cell.getRow().setHeight((short) this.cellHeaderHeight);
            }
            if (this.colWidth > 0) {
                this.sheet.setColumnWidth(colCounter, this.colWidth);
            }
        }
    }

    private void body() {

        if (this.rows == null) {
            log.error("Workbook say: body rows is null, is this correct ?");
            return;
        }

        int rowCounter = 1;
        int colCounter = 0;

        for (List<?> line : this.rows) {

            if (line == null) {
                line = new ArrayList<>();
                log.warn("Workbook say: rows is null, is this correct ?");
            }

            colCounter = 0;
            Row row = this.sheet.createRow(rowCounter++);

            for (Object column : line) {

                if (column == null) {
                    column = "";
                    log.warn("Workbook say: field body is null, is this correct ?");
                }

                Cell cell = row.createCell(colCounter++);
                cell.setCellStyle(this.cellBodyStyle);
                cell.setCellValue(column.toString());

                if (this.cellBodyHeight > 0) {
                    cell.getRow().setHeight((short) this.cellBodyHeight);
                }
                if (this.colWidth > 0) {
                    this.sheet.setColumnWidth(colCounter, this.colWidth);
                }
            }
        }
    }

    public void save(String filepath) throws IOException {
        FileOutputStream outFile = new FileOutputStream(filepath, true);
        this.workbook.write(outFile);
        outFile.close();
    }

    public ByteArrayDataSource bytes() throws IOException {
        return new ByteArrayDataSource(convert(), "application/octet-stream");
    }

    public byte[] convert() throws IOException {
        ByteArrayOutputStream bytArrayOutputStream = new ByteArrayOutputStream();
        this.workbook.write(bytArrayOutputStream);
        return bytArrayOutputStream.toByteArray();
    }

}
