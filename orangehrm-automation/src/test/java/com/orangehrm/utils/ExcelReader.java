package com.orangehrm.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    
    public static List<Object[]> getTestData(String filePath, String sheetName) {
        List<Object[]> testData = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }
            
            int rowCount = sheet.getLastRowNum();
            
            for (int i = 1; i <= rowCount; i++) { // Start from 1 to skip header
                Row row = sheet.getRow(i);
                if (row != null) {
                    String testCaseID = getCellValue(row.getCell(0));
                    String username = getCellValue(row.getCell(1));
                    String password = getCellValue(row.getCell(2));
                    String expectedResult = getCellValue(row.getCell(3));
                    String description = getCellValue(row.getCell(4));
                    
                    testData.add(new Object[]{testCaseID, username, password, expectedResult, description});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read Excel file: " + filePath, e);
        }
        return testData;
    }
    
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}