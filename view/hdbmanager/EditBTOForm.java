package view.hdbmanager;

import project.*;
import project.ProjectRepository;
import view.FormView;
import view.hdbmanager.controller.EditBTOController;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class EditBTOForm extends FormView {
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
        UnitType unitType = null;
        String unitName = "";
        int totalUnits = 0;
        int availableUnits = 0;
        double price = 0;

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
    
//        System.out.print("Unit Name: ");
//        String sUnitType = scanner.next();
//        List<UnitType> units = project.getListOfUnits();
//        String finalSUnitType = sUnitType;
//        unitType = units.stream().filter(u -> u.getName().equalsIgnoreCase(finalSUnitType)).findFirst().orElse(null);
//        if (unitType != null) {
//            System.out.println("Editing Unit...");
//
//            System.out.println("New name: ");
//            unitName = scanner.next();
//
//            System.out.print("Available Units: ");
//            String sAvailableUnits;
//            boolean validAvail = false;
//            do {
//                sAvailableUnits = scanner.next();
//                if (!sAvailableUnits.isEmpty()) {
//                    try {
//                        availableUnits = Integer.parseInt(sAvailableUnits);
//                        validAvail = true;
//                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid number format!");
//                    }
//                }
//                else {
//                    validAvail = true;
//                }
//            } while (!validAvail);
//
//
//            System.out.print("Total Units: ");
//            String sTotalUnits;
//            boolean validTotal = false;
//            do {
//                sTotalUnits = scanner.next();
//                if (!sTotalUnits.isEmpty()) {
//                    try {
//                        totalUnits = Integer.parseInt(sTotalUnits);
//                        validTotal = true;
//                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid number format!");
//                    }
//                }
//                else {
//                    validTotal = true;
//                }
//            } while (!validTotal);
//
//
//            System.out.print("Price Per Unit: ");
//            String sPrice;
//            boolean validPrice = false;
//            do {
//                sPrice = scanner.next();
//                if (!sPrice.isEmpty()) {
//                    try {
//                        price = Double.parseDouble(sPrice);
//                        validPrice = true;
//                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid number format!");
//                    }
//                }
//                else {
//                    validPrice = true;
//                }
//            } while (!validPrice);
//        } else {
//            System.out.println("Invalid unit name");
//        }





        userInput.put("project", project);
        userInput.put("name", name);
        userInput.put("neighbourhood", neighbourhood);
        userInput.put("openingDate", openingDate);
        userInput.put("closingDate", closingDate);
        userInput.put("officerSlots", officerSlots);
        userInput.put("unitType", unitType);
        userInput.put("unitName", unitName);
        userInput.put("totalUnits", totalUnits);
        userInput.put("availableUnits", availableUnits);
        userInput.put("price", price);

        EditBTOController projectController = new EditBTOController(userInput);
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