package application;

import config.Config;
import org.apache.poi.ss.usermodel.*;
import java.io.*;

public class ApplicationRepository {

    //create Application
    public static void createApplication(Application application) {
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("Applications")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            newRow.createCell(0).setCellValue(application.getApplicationID());
            newRow.createCell(1).setCellValue(application.getApplicationDate().toString());
            newRow.createCell(2).setCellValue(application.getApplicationStatus().toString());
            newRow.createCell(3).setCellValue(application.getApplicant().getUid());

            try (FileOutputStream outFile = new FileOutputStream(Config.filepath.get("Applications"))) {
                workbook.write(outFile);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    //update Application
    public static void updateApplication(Application application) {
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("Applications")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            //search for application id and update the relevant row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row.getCell(0).getStringCellValue().equals(application.getApplicationID())) {
                    row.getCell(0).setCellValue(application.getApplicationID());
                    row.getCell(1).setCellValue(application.getApplicationDate().toString());
                    row.getCell(2).setCellValue(application.getApplicationStatus().toString());
                    row.getCell(3).setCellValue(application.getApplicant().getUid());
                }
            }

            try (FileOutputStream outFile = new FileOutputStream(Config.filepath.get("Applications"))) {
                workbook.write(outFile);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
