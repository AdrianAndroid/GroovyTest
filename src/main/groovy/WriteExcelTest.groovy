import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

class WriteExcelTest {
    static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Example Sheet")
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Name")
        row0.createCell(1).setCellValue("Age")


        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("Alice");
        row1.createCell(1).setCellValue(30);

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Bob");
        row2.createCell(1).setCellValue(31);

        FileOutputStream fileOut = new FileOutputStream("/Users/apus/IdeaProjects/GroovyTest/writeexcel.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }
}
