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
import java.util.List;

public class ExcelConverter {
    public static void excelToText(String excelFilePath, String textFilePath) {

        try (FileInputStream inputStream = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(inputStream);
             BufferedWriter writer = new BufferedWriter(new FileWriter(textFilePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();

                for (int i = firstCellNum; i < lastCellNum; i++) {
                    Cell cell = row.getCell(i);
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
            for (int i = 0; i < contents.size(); i++) {
                System.out.println("line = " + i);
                String content = contents.get(i);
                String[] contentArr = content.split("\t");
                for (String str :
                        contentArr) {
                    System.out.println(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
