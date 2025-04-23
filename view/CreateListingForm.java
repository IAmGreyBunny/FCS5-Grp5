package view;

import project.ListingController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class CreateListingForm extends FormView {

    HashMap<String, Object> userInput = new HashMap<>();


    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("--- Create BTO Project ---");
        System.out.println("Enter name of new BTO Project: ");
        String name = scanner.next();
        userInput.put("name",name);

        System.out.println("Enter location: ");
        String location = scanner.next();
        userInput.put("location",location);

        System.out.println("Enter application opening date (dd-MM-yyyy):");
        String sOpeningDate;
        boolean validOpening = false;
        LocalDate openingDate = null;
        do {
            sOpeningDate = scanner.next();
            try {
                openingDate = LocalDate.parse(sOpeningDate, formatter);
                validOpening = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format!");
            }
        } while (!validOpening);
        userInput.put("openingDate", openingDate);

        System.out.println("Enter application closing date (dd-MM-yyyy): ");
        String sClosingDate;
        boolean validClosing = false;
        LocalDate closingDate = null;
        do {
            sClosingDate = scanner.next();
            try {
                closingDate = LocalDate.parse(sClosingDate, formatter);
                validClosing = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format!");
            }
        } while (!validClosing);
        userInput.put("closingDate", closingDate);

        System.out.println("Are there 2-room flat types in this project (Y/N)? ");
        String input2 = scanner.next().toUpperCase();
        switch (input2) {
            case "Y" :
                userInput.put("2-room", "2-room");

                System.out.println("Enter total number of units: ");
                String sTotalUnits;
                boolean validTotal = false;
                int totalUnits = 0;
                do {
                    sTotalUnits = scanner.next();
                    try {
                        totalUnits = Integer.parseInt(sTotalUnits);
                        validTotal = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                } while (!validTotal);
                userInput.put("2-room totalUnits", totalUnits);

                System.out.println("Enter number of available units: ");
                String sAvailableUnits;
                boolean validAvail = false;
                int availableUnits = 0;
                do {
                    sAvailableUnits = scanner.next();
                    try {
                        availableUnits = Integer.parseInt(sAvailableUnits);
                        if (totalUnits >= availableUnits) {
                            validAvail = true;
                        } else {
                            System.out.println("Invalid input! Number of available units cannot be more than total number of units.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                } while (!validAvail);
                userInput.put("2-room availableUnits", availableUnits);

                System.out.println("Enter price per unit: ");
                String sPrice;
                boolean validPrice = false;
                double price = 0;
                do {
                    sPrice = scanner.next();
                    try {
                        price = Double.parseDouble(sPrice);
                        validPrice = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                } while (!validPrice);
                userInput.put("2-room price", price);

                break;
            case "N" :
                userInput.put("2-room", null);
                break;
        }

        System.out.println("Are there 3-room flat types in this project (Y/N)? ");
        String input3 = scanner.next().toUpperCase();
        switch (input3) {
            case "Y" :
                userInput.put("3-room", "3-room");

                System.out.println("Enter total number of units: ");
                String sTotalUnits;
                boolean validTotal = false;
                int totalUnits = 0;
                do {
                    sTotalUnits = scanner.next();
                    try {
                        totalUnits = Integer.parseInt(sTotalUnits);
                        validTotal = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                } while (!validTotal);
                userInput.put("3-room totalUnits", totalUnits);

                System.out.println("Enter number of available units: ");
                String sAvailableUnits;
                boolean validAvail = false;
                int availableUnits = 0;
                do {
                    sAvailableUnits = scanner.next();
                    try {
                        availableUnits = Integer.parseInt(sAvailableUnits);
                        if (totalUnits >= availableUnits) {
                            validAvail = true;
                        } else {
                            System.out.println("Invalid input! Number of available units cannot be more than total number of units.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                } while (!validAvail);
                userInput.put("3-room availableUnits", availableUnits);

                System.out.println("Enter price per unit: ");
                String sPrice;
                boolean validPrice = false;
                double price = 0;
                do {
                    sPrice = scanner.next();
                    try {
                        price = Double.parseDouble(sPrice);
                        validPrice = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                } while (!validPrice);
                userInput.put("3-room price", price);
                break;
            case "N" :
                userInput.put("3-room", null);
                break;
        }

        int officer;
        do {
            System.out.println("Enter number of officer slots (max 10): ");
            officer = scanner.nextInt();
            if (officer<0 || officer>10) {
                System.out.println("Invalid number!");
            }
            else {break;}
        } while (true);
        userInput.put("officerSlots", officer);

        System.out.println("Creating project...");
        ListingController.create(this.getUserInput());

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