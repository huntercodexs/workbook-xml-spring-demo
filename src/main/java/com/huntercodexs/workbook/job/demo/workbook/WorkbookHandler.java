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

    public void prepare(
            String sheetTitle,
            List<String> cols,
            ArrayList<List<?>> rows
    ) {
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet(sheetTitle);
        this.cols = cols;
        this.rows = rows;
    }

    public void createHeader(boolean autoSize) {
        this.header(autoSize);
    }

    public void createBody(boolean autoSize) {
        this.body(autoSize);
    }

    private void header(boolean autoSize) {

        int rowCounter = 0;
        int colCounter = 0;

        Row row = this.sheet.createRow(rowCounter++);

        for (Object column : this.cols) {
            Cell cell = row.createCell(colCounter++);
            cell.setCellStyle(this.cellCurrentStyle);
            cell.setCellValue(column.toString().toUpperCase());
            this.sheet.setColumnWidth(colCounter, this.colWidth);
        }
    }

    private void body(boolean autoSize) {

        int rowCounter = 1;
        int colCounter = 0;

        for (List<?> line : this.rows) {

            colCounter = 0;
            Row row = this.sheet.createRow(rowCounter++);

            for (Object column : line) {
                Cell cell = row.createCell(colCounter++);
                cell.setCellStyle(this.cellCurrentStyle);
                cell.setCellValue(column.toString());
                this.sheet.setColumnWidth(colCounter, this.colWidth);
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
