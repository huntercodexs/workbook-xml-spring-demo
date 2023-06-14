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
public class WorkbookStyles extends WorkbookColors {

    protected XSSFWorkbook workbook;
    protected XSSFSheet sheet;
    protected XSSFCellStyle cellHeaderStyle;
    protected XSSFCellStyle cellBodyStyle;
    protected XSSFCellStyle cellCurrentStyle;
    protected Font fontHeader;
    protected Font fontBody;
    protected Font fontCurrent;
    protected int colWidth = 0;
    protected int cellHeaderHeight;
    protected int cellBodyHeight;
    protected int cellCurrentHeight = 0;
    protected boolean headerActive;
    protected boolean bodyActive;

    public final WorkbookStyles toHeader() {
        bodyActive = false;
        headerActive = true;
        if (this.fontHeader == null) {
            this.fontHeader = this.workbook.createFont();
            this.fontCurrent = this.fontHeader;
        }
        if (this.cellHeaderStyle == null) {
            this.cellHeaderStyle = this.workbook.createCellStyle();
            this.cellCurrentStyle = this.cellHeaderStyle;
        }
        if (this.cellCurrentHeight > 0) this.cellCurrentHeight = 0;
        return this;
    }

    public final WorkbookStyles toBody() {
        bodyActive = true;
        headerActive = false;
        if (this.fontBody == null) {
            this.fontBody = this.workbook.createFont();
            this.fontCurrent = this.fontBody;
        }
        if (this.cellBodyStyle == null) {
            this.cellBodyStyle = this.workbook.createCellStyle();
            this.cellCurrentStyle = this.cellBodyStyle;
        }
        if (this.cellCurrentHeight > 0) this.cellCurrentHeight = 0;
        return this;
    }

    public void reset() {
        this.fontBody = null;
        this.fontHeader = null;
        this.fontCurrent = null;
        this.cellBodyStyle = null;
        this.cellHeaderStyle = null;
        this.cellCurrentStyle = null;
        this.colWidth = 0;
        this.cellBodyHeight = 0;
        this.cellHeaderHeight = 0;
        this.cellCurrentHeight = 0;
        bodyActive = false;
        headerActive = false;
    }

    public void backColor(String color) {
        this.cellCurrentStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        switch (color) {
            case "white" -> {
                this.cellCurrentStyle.setFillBackgroundColor(WHITE);
                this.cellCurrentStyle.setFillForegroundColor(WHITE);
            }
            case "black" -> {
                this.cellCurrentStyle.setFillBackgroundColor(BLACK);
                this.cellCurrentStyle.setFillForegroundColor(BLACK);
            }
            case "blue" -> {
                this.cellCurrentStyle.setFillBackgroundColor(BLUE);
                this.cellCurrentStyle.setFillForegroundColor(BLUE);
            }
            case "red" -> {
                this.cellCurrentStyle.setFillBackgroundColor(RED);
                this.cellCurrentStyle.setFillForegroundColor(RED);
            }
            case "yellow" -> {
                this.cellCurrentStyle.setFillBackgroundColor(YELLOW);
                this.cellCurrentStyle.setFillForegroundColor(YELLOW);
            }
            case "pink" -> {
                this.cellCurrentStyle.setFillBackgroundColor(PINK);
                this.cellCurrentStyle.setFillForegroundColor(PINK);
            }
            case "green" -> {
                this.cellCurrentStyle.setFillBackgroundColor(GREEN);
                this.cellCurrentStyle.setFillForegroundColor(GREEN);
            }
            case "orange" -> {
                this.cellCurrentStyle.setFillBackgroundColor(ORANGE);
                this.cellCurrentStyle.setFillForegroundColor(ORANGE);
            }
            case "aqua" -> {
                this.cellCurrentStyle.setFillBackgroundColor(AQUA);
                this.cellCurrentStyle.setFillForegroundColor(AQUA);
            }
            case "brown" -> {
                this.cellCurrentStyle.setFillBackgroundColor(BROWN);
                this.cellCurrentStyle.setFillForegroundColor(BROWN);
            }
            case "coral" -> {
                this.cellCurrentStyle.setFillBackgroundColor(CORAL);
                this.cellCurrentStyle.setFillForegroundColor(CORAL);
            }
            case "silver-light" -> {
                this.cellCurrentStyle.setFillBackgroundColor(SILVER_LIGHT);
                this.cellCurrentStyle.setFillForegroundColor(SILVER_LIGHT);
            }
            case "silver" -> {
                this.cellCurrentStyle.setFillBackgroundColor(SILVER);
                this.cellCurrentStyle.setFillForegroundColor(SILVER);
            }
            case "grey" -> {
                this.cellCurrentStyle.setFillBackgroundColor(GREY);
                this.cellCurrentStyle.setFillForegroundColor(GREY);
            }
            case "dark-silver" -> {
                this.cellCurrentStyle.setFillBackgroundColor(DARK_SILVER);
                this.cellCurrentStyle.setFillForegroundColor(DARK_SILVER);
            }
            case "gold" -> {
                this.cellCurrentStyle.setFillBackgroundColor(GOLD);
                this.cellCurrentStyle.setFillForegroundColor(GOLD);
            }
            case "indigo" -> {
                this.cellCurrentStyle.setFillBackgroundColor(INDIGO);
                this.cellCurrentStyle.setFillForegroundColor(INDIGO);
            }
            case "purple" -> {
                this.cellCurrentStyle.setFillBackgroundColor(PURPLE);
                this.cellCurrentStyle.setFillForegroundColor(PURPLE);
            }
            case "lime" -> {
                this.cellCurrentStyle.setFillBackgroundColor(LIME);
                this.cellCurrentStyle.setFillForegroundColor(LIME);
            }
            case "rose" -> {
                this.cellCurrentStyle.setFillBackgroundColor(ROSE);
                this.cellCurrentStyle.setFillForegroundColor(ROSE);
            }
            default -> throw new RuntimeException("Invalid color to background: " + color);
        }

        styles();
    }

    public void fontColor(String color) {
        switch (color) {
            case "white" -> {
                this.fontCurrent.setColor(WHITE);
            }
            case "black" -> {
                this.fontCurrent.setColor(BLACK);
            }
            case "blue" -> {
                this.fontCurrent.setColor(BLUE);
            }
            case "red" -> {
                this.fontCurrent.setColor(RED);
            }
            case "yellow" -> {
                this.fontCurrent.setColor(YELLOW);
            }
            case "pink" -> {
                this.fontCurrent.setColor(PINK);
            }
            case "green" -> {
                this.fontCurrent.setColor(GREEN);
            }
            case "orange" -> {
                this.fontCurrent.setColor(ORANGE);
            }
            case "aqua" -> {
                this.fontCurrent.setColor(AQUA);
            }
            case "brown" -> {
                this.fontCurrent.setColor(BROWN);
            }
            case "coral" -> {
                this.fontCurrent.setColor(CORAL);
            }
            case "silver-light" -> {
                this.fontCurrent.setColor(SILVER_LIGHT);
            }
            case "silver" -> {
                this.fontCurrent.setColor(SILVER);
            }
            case "grey" -> {
                this.fontCurrent.setColor(GREY);
            }
            case "dark-silver" -> {
                this.fontCurrent.setColor(DARK_SILVER);
            }
            case "gold" -> {
                this.fontCurrent.setColor(GOLD);
            }
            case "indigo" -> {
                this.fontCurrent.setColor(INDIGO);
            }
            case "purple" -> {
                this.fontCurrent.setColor(PURPLE);
            }
            case "lime" -> {
                this.fontCurrent.setColor(LIME);
            }
            case "rose" -> {
                this.fontCurrent.setColor(ROSE);
            }
            default -> throw new RuntimeException("Invalid color to font: " + color);
        }

        this.cellCurrentStyle.setFont(this.fontCurrent);

        styles();

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

        styles();

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

        styles();

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

        styles();

    }

    public void fontSize(Short size) {
        this.fontCurrent.setFontHeightInPoints(size);
        this.cellCurrentStyle.setFont(this.fontCurrent);
        styles();
    }

    public void cellBorder(String border, String style) {

        BorderStyle borderStyle;

        switch (style) {
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

        styles();

    }

    public void cellWidth(int width) {
        this.colWidth = 25 * width;
    }

    public void cellHeight(int height) {
        this.cellCurrentHeight = height;
        height();
    }

    public XSSFCellStyle getCellStyle() {
        return this.cellCurrentStyle;
    }

    private void height() {
        if (headerActive) this.cellHeaderHeight = this.cellCurrentHeight;
        if (bodyActive) this.cellBodyHeight = this.cellCurrentHeight;
    }

    private void styles() {
        if (headerActive) this.cellHeaderStyle = this.cellCurrentStyle;
        if (bodyActive) this.cellBodyStyle = this.cellCurrentStyle;
    }

}
