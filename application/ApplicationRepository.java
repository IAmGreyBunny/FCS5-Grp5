package application;

import config.Config;
import org.apache.poi.ss.usermodel.*;
import user.User;
import user.UserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ApplicationRepository {

    public static void createApplication(Application app) {
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("Applications")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);
            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);

            newRow.createCell(0).setCellValue(app.getApplicationID());
            newRow.createCell(1).setCellValue(app.getClass().getSimpleName());
            newRow.createCell(2).setCellValue(app.getApplicationDate().toString());
            newRow.createCell(3).setCellValue(app.getApplicationStatus().toString());
            newRow.createCell(4).setCellValue(app.getApplicant().getUid());

            if (app instanceof BTOApplication bto) {
                newRow.createCell(5).setCellValue(bto.getProjectId());
            } else {
                newRow.createCell(5).setCellValue("");
            }

            try (FileOutputStream out = new FileOutputStream(Config.filepath.get("Applications"))) {
                workbook.write(out);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void updateApplication(Application app) {
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("Applications")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0).getStringCellValue().equals(app.getApplicationID())) {
                    row.getCell(2).setCellValue(app.getApplicationDate().toString());
                    row.getCell(3).setCellValue(app.getApplicationStatus().toString());

                    if (app instanceof BTOApplication bto) {
                        row.getCell(5).setCellValue(bto.getProjectId());
                    }

                    break;
                }
            }

            try (FileOutputStream out = new FileOutputStream(Config.filepath.get("Applications"))) {
                workbook.write(out);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static List<Application> getAllApplications() {
        List<Application> applications = new ArrayList<>();

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("Applications")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String id = row.getCell(0).getStringCellValue();
                String type = row.getCell(1).getStringCellValue();
                LocalDate date = LocalDate.parse(row.getCell(2).getStringCellValue());
                ApplicationStatus status = ApplicationStatus.valueOf(row.getCell(3).getStringCellValue());
                int applicantID = (int) row.getCell(4).getNumericCellValue();

                User user = UserRepository.findUserById(applicantID);
                if (user == null) continue;

                if (type.equals("BTOApplication")) {
                    String projectId = row.getCell(5).getStringCellValue();
                    BTOApplication bto = new BTOApplication(id, date, user, projectId, String.valueOf(applicantID), null);
                    bto.setApplicationStatus(status);
                    applications.add(bto);
                } else {
                    Application basic = new Application(id, date, user) {};
                    basic.setApplicationStatus(status);
                    applications.add(basic);
                }
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return applications;
    }
}