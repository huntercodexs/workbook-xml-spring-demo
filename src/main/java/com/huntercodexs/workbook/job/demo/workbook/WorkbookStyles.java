package com.huntercodexs.workbook.job.demo.workbook;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WorkbookStyles {

    protected XSSFWorkbook workbook;
    protected XSSFSheet sheet;
    protected XSSFCellStyle cellHeaderStyle;
    protected XSSFCellStyle cellBodyStyle;
    protected XSSFCellStyle cellCurrentStyle;
    protected Font fontHeader;
    protected Font fontBody;
    protected Font fontCurrent;
    protected int colWidth;
    protected short rowHeightHeader;
    protected short rowHeightBody;
    protected short rowHeightCurrent;

    public final WorkbookStyles toHeader() {
        if (this.fontHeader == null) {
            this.fontHeader = this.workbook.createFont();
            this.fontCurrent = this.fontHeader;
        }
        if (this.cellHeaderStyle == null) {
            this.cellHeaderStyle = this.workbook.createCellStyle();
            this.cellCurrentStyle = this.cellHeaderStyle;
        }
        return this;
    }

    public final WorkbookStyles toBody() {
        if (this.fontBody == null) {
            this.fontBody = this.workbook.createFont();
            this.fontCurrent = this.fontBody;
        }
        if (this.cellBodyStyle == null) {
            this.cellBodyStyle = this.workbook.createCellStyle();
            this.cellCurrentStyle = this.cellBodyStyle;
        }
        return this;
    }

    public void backColor(String color) {
        this.cellCurrentStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        switch (color) {
            case "white" -> {
                this.cellCurrentStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
                this.cellCurrentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            }
            case "blue" -> {
                this.cellCurrentStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
                this.cellCurrentStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            }
            case "black" -> {
                this.cellCurrentStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
                this.cellCurrentStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
            }
            case "red" -> {
                this.cellCurrentStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
                this.cellCurrentStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            }
            case "yellow" -> {
                this.cellCurrentStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
                this.cellCurrentStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            }
            case "green" -> {
                this.cellCurrentStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                this.cellCurrentStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            }
            case "pink" -> {
                this.cellCurrentStyle.setFillBackgroundColor(IndexedColors.PINK.getIndex());
                this.cellCurrentStyle.setFillForegroundColor(IndexedColors.PINK.getIndex());
            }
            case "orange" -> {
                this.cellCurrentStyle.setFillBackgroundColor(IndexedColors.ORANGE.getIndex());
                this.cellCurrentStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            }
            default -> throw new RuntimeException("Invalid color to background: " + color);
        }
    }

    public void fontColor(String color) {
        switch (color) {
            case "white" -> {
                this.fontCurrent.setColor(IndexedColors.WHITE.getIndex());
            }
            case "blue" -> {
                this.fontCurrent.setColor(IndexedColors.BLUE.getIndex());
            }
            case "black" -> {
                this.fontCurrent.setColor(IndexedColors.BLACK.getIndex());
            }
            case "red" -> {
                this.fontCurrent.setColor(IndexedColors.RED.getIndex());
            }
            case "yellow" -> {
                this.fontCurrent.setColor(IndexedColors.YELLOW.getIndex());
            }
            case "green" -> {
                this.fontCurrent.setColor(IndexedColors.GREEN.getIndex());
            }
            case "pink" -> {
                this.fontCurrent.setColor(IndexedColors.PINK.getIndex());
            }
            case "orange" -> {
                this.fontCurrent.setColor(IndexedColors.ORANGE.getIndex());
            }
            default -> throw new RuntimeException("Invalid color to font: " + color);
        }
        this.cellCurrentStyle.setFont(this.fontCurrent);
    }

    public void vAlign(String direction) {
        switch (direction) {
            case "left" -> {
                this.cellCurrentStyle.setAlignment(HorizontalAlignment.LEFT);
            }
            case "right" -> {
                this.cellCurrentStyle.setAlignment(HorizontalAlignment.RIGHT);
            }
            case "center" -> {
                this.cellCurrentStyle.setAlignment(HorizontalAlignment.CENTER);
            }
            case "justify" -> {
                this.cellCurrentStyle.setAlignment(HorizontalAlignment.JUSTIFY);
            }
            default -> throw new RuntimeException("Invalid direction to vAlign: " + direction);
        }
    }

    public void hAlign(String direction) {
        switch (direction) {
            case "top" -> {
                this.cellCurrentStyle.setVerticalAlignment(VerticalAlignment.TOP);
            }
            case "middle" -> {
                this.cellCurrentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            }
            case "bottom" -> {
                this.cellCurrentStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
            }
            default -> throw new RuntimeException("Invalid direction to hAlign: " + direction);
        }
    }

    public void weight(String option) {
        switch (option) {
            case "bold" -> {
                this.fontCurrent.setBold(true);
            }
            case "italic" -> {
                this.fontCurrent.setItalic(true);
            }
            case "normal" -> {
                this.fontCurrent.setBold(false);
            }
            case "underline" -> {
                this.fontCurrent.setUnderline(HSSFFont.U_DOUBLE);
            }
            default -> throw new RuntimeException("Invalid option to weight: " + option);
        }
        this.cellCurrentStyle.setFont(this.fontCurrent);
    }

    public void fontSize(Short size) {
        this.fontCurrent.setFontHeightInPoints(size);
        this.cellCurrentStyle.setFont(this.fontCurrent);
    }

    public void border(String border, String type) {

        BorderStyle borderStyle;

        switch (type) {
            case "solid" -> {
                borderStyle = BorderStyle.THIN;
            }
            case "dashed" -> {
                borderStyle = BorderStyle.DASHED;
            }
            case "dotted" -> {
                borderStyle = BorderStyle.DOTTED;
            }
            case "double" -> {
                borderStyle = BorderStyle.DOUBLE;
            }
            case "medium" -> {
                borderStyle = BorderStyle.MEDIUM;
            }
            default -> {
                borderStyle = BorderStyle.NONE;
            }
        }

        switch (border) {
            case "top" -> {
                this.cellCurrentStyle.setBorderTop(borderStyle);
            }
            case "right" -> {
                this.cellCurrentStyle.setBorderRight(borderStyle);
            }
            case "bottom" -> {
                this.cellCurrentStyle.setBorderBottom(borderStyle);
            }
            case "left" -> {
                this.cellCurrentStyle.setBorderLeft(borderStyle);
            }
            case "full" -> {
                this.cellCurrentStyle.setBorderTop(borderStyle);
                this.cellCurrentStyle.setBorderRight(borderStyle);
                this.cellCurrentStyle.setBorderBottom(borderStyle);
                this.cellCurrentStyle.setBorderLeft(borderStyle);
            }
            default -> throw new RuntimeException("Invalid value to border: " + border);
        }
    }

    public void width(int colWidth) {
        this.colWidth = 25 * colWidth;
    }

    public void height(short rowHeight) {
        this.rowHeightCurrent = rowHeight;
    }

    public XSSFCellStyle getCellStyle() {
        return this.cellCurrentStyle;
    }

}
