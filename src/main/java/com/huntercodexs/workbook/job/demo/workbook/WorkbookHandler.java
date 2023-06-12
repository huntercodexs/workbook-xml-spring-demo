package com.huntercodexs.workbook.job.demo.workbook;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.List;

@Slf4j
@Service
public class WorkbookHandler {

    public XSSFWorkbook create(List<? extends ProductDto> productDto) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        sheet(workbook, productDto, "SHEET TITLE");
        return workbook;
    }

    private void sheet(XSSFWorkbook workbook, List<? extends ProductDto> productDto, String sheetTitle) {
        XSSFSheet sheet = workbook.createSheet(sheetTitle);
        filler(productDto, sheet);
    }

    private void filler(List<? extends ProductDto> productDto, XSSFSheet sheet) {

        int rowNum = 0;
        int colNum = 0;

        Row row = sheet.createRow(rowNum++);

        row.createCell(colNum++).setCellValue("Id");
        row.createCell(colNum++).setCellValue("Name");
        row.createCell(colNum++).setCellValue("Description");
        row.createCell(colNum++).setCellValue("Price");

        for (ProductDto dtoItem : productDto) {

            colNum = 0;
            row = sheet.createRow(rowNum++);

            row.createCell(colNum++).setCellValue(dtoItem.getId());
            row.createCell(colNum++).setCellValue(dtoItem.getName());
            row.createCell(colNum++).setCellValue(dtoItem.getDescription());
            row.createCell(colNum++).setCellValue(dtoItem.getPrice());
            
        }
    }

    public void save(String filename, XSSFWorkbook workbook) throws IOException {
        FileOutputStream outFile = new FileOutputStream(filename, true);
        workbook.write(outFile);
        outFile.close();
    }

    public ByteArrayDataSource bytes(XSSFWorkbook workbook) throws IOException {
        return new ByteArrayDataSource(
                convert(workbook), "application/octet-stream");
    }

    public byte[] convert(XSSFWorkbook workbook) throws IOException {
        ByteArrayOutputStream bytArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(bytArrayOutputStream);
        return bytArrayOutputStream.toByteArray();
    }

}
