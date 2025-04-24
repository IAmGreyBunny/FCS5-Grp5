package view.form;

import project.ProjectController;
import validator.InputValidator;
import view.FormView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class CreateListingForm extends FormView {

    HashMap<String, Object> userInput = new HashMap<>();

    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("--- Create BTO Project ---");
        System.out.println("Enter name of new BTO Project: ");
        String name = scanner.next();
        userInput.put("projectName", name);

        System.out.println("Enter neighbourhood: ");
        String neighbourhood = scanner.next();
        userInput.put("neighbourhood", neighbourhood);

        // Get opening date
        System.out.println("Enter application opening date (dd/MM/yyyy):");
        String openingDateString;
        LocalDate openingDate = null;
        do {
            openingDateString = scanner.next();
            if (InputValidator.validateDate(openingDateString, "dd/MM/yyyy")) {
                openingDate = LocalDate.parse(openingDateString, formatter);
            } else {
                System.out.println("Invalid date format!");
            }
        } while (openingDate == null);
        userInput.put("applicationOpeningDate", openingDate);

        // Get closing date
        System.out.println("Enter application closing date (dd/MM/yyyy): ");
        String closingDateString;
        LocalDate closingDate = null;
        do {
            closingDateString = scanner.next();
            if (InputValidator.validateDate(closingDateString, "dd/MM/yyyy")) {
                closingDate = LocalDate.parse(closingDateString, formatter);
                if (!InputValidator.validateDateRange(openingDate, closingDate)) {
                    System.out.println("Closing date must be after opening date!");
                    closingDate = null;
                }
            } else {
                System.out.println("Invalid date format!");
            }
        } while (closingDate == null);
        userInput.put("applicationClosingDate", closingDate);

        // Get officer slots
        System.out.println("Enter number of officer slots (max 10): ");
        String sOfficer;
        int officer = 0;
        boolean validOfficer = false;
        do {
            sOfficer = scanner.next();
            if (InputValidator.validateIntRange(sOfficer, 0, 10)) {
                officer = Integer.parseInt(sOfficer);
                validOfficer = true;
            } else {
                System.out.println("Invalid number! Must be between 0 and 10.");
            }
        } while (!validOfficer);
        userInput.put("officerSlots", officer);

        System.out.println("Creating project...");
        ProjectController.createListing(this.getUserInput());
    }

    @Override
    public HashMap<String, Object> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }
}