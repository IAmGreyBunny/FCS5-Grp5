package project;

import config.Config;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProjectRepository {

    public static ArrayList<Project> getAllProjects()
    {
        ArrayList<Project> listOfProjects = new ArrayList<>();

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectDetails")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                int projectId = (int) row.getCell(0).getNumericCellValue();
                String projectName = row.getCell(1).getStringCellValue();
                String neighbourhood = row.getCell(2).getStringCellValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                LocalDate openingDate = LocalDate.parse(row.getCell(3).getStringCellValue(),formatter);
                LocalDate closingDate = LocalDate.parse(row.getCell(4).getStringCellValue(),formatter);
                int officerSlots = (int) row.getCell(5).getNumericCellValue();
                boolean visibility = row.getCell(6).getBooleanCellValue();

                Project project = new Project(projectId,projectName,neighbourhood,openingDate,closingDate,officerSlots,visibility);
                listOfProjects.add(project);

            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return listOfProjects;
    }

    public ArrayList<Integer> getProjectOfficersId(int projectId){
        ArrayList<Integer> projectOfficersId = new ArrayList<>();

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectOfficer")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(0).getNumericCellValue() == projectId){
                    projectOfficersId.add((int) row.getCell(1).getNumericCellValue());
                }

            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return projectOfficersId;
    }

    public static int getProjectManagerId(int projectId){
        int managerId = 0;

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectManager")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(0).getNumericCellValue() == projectId){
                    managerId = (int) row.getCell(1).getNumericCellValue();
                }

            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return managerId;
    }
}
