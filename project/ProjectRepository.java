package project;

import config.Config;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProjectRepository {

    public static void createProject(Project project) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectDetails")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            int projectId = project.getProjectId();
            String name = project.getProjectName();
            String neighbourhood = project.getNeighbourhood();
            String openingDateString = project.getApplicationOpeningDate().format(formatter);
            String closingDateString = project.getApplicationClosingDate().format(formatter);
            int officerSlots = project.getOfficerSlots();
            boolean visibility = project.getVisibility();

            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            newRow.createCell(0).setCellValue(projectId);
            newRow.createCell(1).setCellValue(name);
            newRow.createCell(2).setCellValue(neighbourhood);
            newRow.createCell(3).setCellValue(openingDateString);
            newRow.createCell(4).setCellValue(closingDateString);
            newRow.createCell(5).setCellValue(officerSlots);
            newRow.createCell(6).setCellValue(visibility);

            try (FileOutputStream outFile = new FileOutputStream(Config.filepath.get("ProjectDetails"))) {
                workbook.write(outFile);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void assignUnitType(UnitType unitType, int projectId) {

    }

    public static void updateProject(Project project) {
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectDetails")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            int projectId = project.getProjectId();
            String name = project.getProjectName();
            String neighbourhood = project.getNeighbourhood();
            String openingDateString = project.getApplicationOpeningDate().format(formatter);
            String closingDateString = project.getApplicationClosingDate().format(formatter);
            int officerSlots = project.getOfficerSlots();
            boolean visibility = project.getVisibility();

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(0).getNumericCellValue() == projectId) {
                    row.getCell(0).setCellValue(projectId);
                    row.getCell(1).setCellValue(name);
                    row.getCell(2).setCellValue(neighbourhood);
                    row.getCell(3).setCellValue(openingDateString);
                    row.getCell(4).setCellValue(closingDateString);
                    row.getCell(5).setCellValue(officerSlots);
                    row.getCell(6).setCellValue(visibility);

                }
            }

            try (FileOutputStream outFile = new FileOutputStream(Config.filepath.get("ProjectDetails"))) {
                workbook.write(outFile);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static ArrayList<Project> getAllProjects() {
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate openingDate = LocalDate.parse(row.getCell(3).getStringCellValue(), formatter);
                LocalDate closingDate = LocalDate.parse(row.getCell(4).getStringCellValue(), formatter);
                int officerSlots = (int) row.getCell(5).getNumericCellValue();
                boolean visibility = row.getCell(6).getBooleanCellValue();

                Project project = new Project(projectId, projectName, neighbourhood, openingDate, closingDate, officerSlots, visibility);
                listOfProjects.add(project);

            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return listOfProjects;
    }

    public static ArrayList<UnitType> getAllUnitsType() {
        ArrayList<UnitType> listOfUnits = new ArrayList<>();

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UnitType")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                int unitTypeId = (int) row.getCell(0).getNumericCellValue();
                int projectId = (int) row.getCell(1).getNumericCellValue();
                String name = row.getCell(2).getStringCellValue();
                int sellingPrice = (int) row.getCell(1).getNumericCellValue();
                int available = (int) row.getCell(1).getNumericCellValue();
                int total = (int) row.getCell(1).getNumericCellValue();

                UnitType unitType = new UnitType(unitTypeId, projectId, name, sellingPrice, available, total);
                listOfUnits.add(unitType);

            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return listOfUnits;
    }

    public static Project getProjectById(int id) {
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectDetails")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(0).getNumericCellValue() == id) {
                    int projectId = (int) row.getCell(0).getNumericCellValue();
                    String projectName = row.getCell(1).getStringCellValue();
                    String neighbourhood = row.getCell(2).getStringCellValue();
                    LocalDate openingDate = row.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate closingDate = row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    int officerSlots = (int) row.getCell(5).getNumericCellValue();
                    boolean visibility = row.getCell(6).getBooleanCellValue();

                    Project project = new Project(projectId, projectName, neighbourhood, openingDate, closingDate, officerSlots, visibility);
                    return project;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Integer> getProjectOfficersId(int projectId) {
        ArrayList<Integer> projectOfficersId = new ArrayList<>();

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectOfficer")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(0).getNumericCellValue() == projectId) {
                    projectOfficersId.add((int) row.getCell(1).getNumericCellValue());
                }

            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return projectOfficersId;
    }

    public static ArrayList<Integer> getOfficerProjectsId(int uid) {
        ArrayList<Integer> listOfProjectId = new ArrayList<>();

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectOfficer")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(1).getNumericCellValue() == uid) {
                    listOfProjectId.add((int) row.getCell(0).getNumericCellValue());
                }

            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return listOfProjectId;
    }

    public static int getProjectManagerId(int projectId) {
        int managerId = 0;

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectManager")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(0).getNumericCellValue() == projectId) {
                    managerId = (int) row.getCell(1).getNumericCellValue();
                }

            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return managerId;
    }

    public static int findMaxId() {
        int maxId = 0;
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("ProjectDetails")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if ((int) row.getCell(0).getNumericCellValue() > maxId) {
                    maxId = (int) row.getCell(0).getNumericCellValue();
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return maxId;
    }
}
