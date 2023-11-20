package com.mademo.view2table.tool;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ExcelConverter {
    public static void excelToText(String excelFilePath, String textFilePath) {

        try (FileInputStream inputStream = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(inputStream);
             BufferedWriter writer = new BufferedWriter(new FileWriter(textFilePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();

            Row firstRow = sheet.getRow(0);
            int lastCellNum = firstRow.getLastCellNum();

            for (int i = 0; i < lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    writer.write(null == cell ? "" : cell.toString());
                    writer.write('\t');
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String excelFilePath = "test.xlsx";
        String textFilePath = "text.txt";

        excelToText(excelFilePath, textFilePath);
        try {
            List<String> contents = Files.readAllLines(Paths.get(textFilePath));

            contents.forEach(content -> {
                Arrays.stream(content.split("\t")).forEach(s -> {
                    System.out.print(s);
                    System.out.print(" ");
                });
                System.out.println();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
