package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    private String filePath;
    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtility(String filePath, String sheetName) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        fis.close();
    }

    // Get row count
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Get column count
    public int getColCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    // Get cell data
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Set cell data
    public void setCellData(int rowNum, int colNum, String value) throws IOException {
        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        Cell cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        cell.setCellValue(value);

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }

    // Close workbook
    public void close() throws IOException {
        workbook.close();
    }
}
