package view.form;

import project.*;
import project.ProjectRepository;
import view.FormView;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;

public class EditListingForm extends FormView {
    HashMap<String, Object> userInput = new HashMap<>();

    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Project project;
        String name = "";
        String neighbourhood = "";
        LocalDate openingDate = null;
        LocalDate closingDate = null;
        int officerSlots = 0;
        int total2Units = 0;
        int available2Units = 0;
        double price2 = 0;
        int total3Units = 0;
        int available3Units = 0;
        double price3 = 0;

        System.out.println("Enter project id to be edited: ");
        String projectId;

        do {
            projectId = scanner.next();
            ArrayList<Project> projects = ProjectRepository.getAllProjects();
            int finalProjectId = Integer.parseInt(projectId);
            project = projects.stream().filter(p -> p.getProjectId()==(finalProjectId)).findFirst().orElse(null);
            if (project != null) {
                System.out.println("Editing Project...");
            } else {
                System.out.println("Invalid project name.");
            }
        } while (project == null);

        System.out.println("=== Edit BTO Project ===");
        System.out.println("Leave field empty to keep current values.");
        
        System.out.println("Project name: ");
        name = scanner.next();

        System.out.println("Neighbourhood: ");
        neighbourhood = scanner.next();

        System.out.println("Application opening date (dd-MM-yyyy): ");
        String sOpeningDate;
        boolean validOpening = false;
        do {
            sOpeningDate = scanner.next();
            if (!sOpeningDate.isEmpty()) {
                try {
                    openingDate = LocalDate.parse(sOpeningDate, formatter);
                    validOpening = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format!");
                }
            }
            else {
                validOpening = true;
            }
        } while (!validOpening);

        System.out.println("Application closing date (dd-MM-yyyy): ");
        String sClosingDate;
        boolean validClosing = false;
        do {
            sClosingDate = scanner.next();
            if (!sClosingDate.isEmpty()) {
                try {
                    closingDate = LocalDate.parse(sClosingDate, formatter);
                    validClosing = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format!");
                }
            }
            else {
                validClosing = true;
            }
        } while (!validClosing);

        System.out.println("Officer slots: ");
        String sOfficerSlots;
        boolean validSlots = false;
        do {
            sOfficerSlots = scanner.next();
            if (!sOfficerSlots.isEmpty()) {
                try {
                    officerSlots = Integer.parseInt(sOfficerSlots);
                    validSlots = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format!");
                }
            }
            else {
                validSlots = true;
            }
        } while (!validSlots);


        System.out.println("\n=== Editing Unit Types ===");
        System.out.println("Leave field empty to keep current values");

//        System.out.println("--- 2 Room Units ---");
//        System.out.println("Enter total number of units: ");
//        String s2Total;
//        boolean valid2Total = false;
//        do {
//            s2Total = scanner.next();
//            if (!s2Total.isEmpty()) {
//                try {
//                    total2Units = Integer.parseInt(s2Total);
//                    valid2Total = true;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid number format");
//                }
//            } else {
//                valid2Total = true;
//            }
//        } while (!valid2Total);
//
//        System.out.println("Enter available number of units: ");
//        String s2Avail;
//        boolean valid2Avail = false;
//        do {
//            s2Avail = scanner.next();
//            if (!s2Avail.isEmpty()) {
//                try {
//                    available2Units = Integer.parseInt(s2Avail);
//                    valid2Avail = true;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid number format");
//                }
//            } else {
//                valid2Avail = true;
//            }
//        } while (!valid2Avail);
//
//        System.out.println("Enter price per unit: ");
//        String sPrice2;
//        boolean validPrice2 = false;
//        do {
//            sPrice2 = scanner.next();
//            if (!sPrice2.isEmpty()) {
//                try {
//                    price2 = Integer.parseInt(sPrice2);
//                    validPrice2 = true;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid number format");
//                }
//            } else {
//                validPrice2 = true;
//            }
//        } while (!validPrice2);
//
//        System.out.println("--- 3 Room Units ---");
//        System.out.println("Enter total number of units: ");
//        String s3Total;
//        boolean valid3Total = false;
//        do {
//            s3Total = scanner.next();
//            if (!s3Total.isEmpty()) {
//                try {
//                    total3Units = Integer.parseInt(s3Total);
//                    valid3Total = true;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid number format");
//                }
//            } else {
//                valid3Total = true;
//            }
//        } while (!valid3Total);
//
//        System.out.println("Enter available number of units: ");
//        String s3Avail;
//        boolean valid3Avail = false;
//        do {
//            s3Avail = scanner.next();
//            if (!s3Avail.isEmpty()) {
//                try {
//                    available3Units = Integer.parseInt(s3Avail);
//                    valid3Avail = true;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid number format");
//                }
//            } else {
//                valid3Avail = true;
//            }
//        } while (!valid3Avail);
//
//        System.out.println("Enter price per unit: ");
//        String sPrice3;
//        boolean validPrice3 = false;
//        do {
//            sPrice3 = scanner.next();
//            if (!sPrice3.isEmpty()) {
//                try {
//                    price3 = Integer.parseInt(sPrice3);
//                    validPrice3 = true;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid number format");
//                }
//            } else {
//                validPrice3 = true;
//            }
//        } while (!validPrice3);
    




        userInput.put("project", project);
        userInput.put("name", name);
        userInput.put("neighbourhood", neighbourhood);
        userInput.put("openingDate", openingDate);
        userInput.put("closingDate", closingDate);
        userInput.put("officerSlots", officerSlots);
        userInput.put("2-room totalUnits", total2Units);
        userInput.put("2-room availableUnits", available2Units);
        userInput.put("2-room price", price2);
        userInput.put("3-room totalUnits", total3Units);
        userInput.put("3-room availableUnits", available3Units);
        userInput.put("3-room price", price3);

        ProjectController projectController = new ProjectController(userInput);
        projectController.editListing();

    }

    @Override
    public HashMap<String,Object> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }

    
}