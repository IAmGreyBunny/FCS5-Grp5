package view.form;

import validator.InputValidator;
import view.FormView;

import java.util.HashMap;
import java.util.Scanner;

public class CreateUnitTypeForm extends FormView {

    HashMap<String,Object> userInput = new HashMap<>();
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Override
    public void prompt() {
        // Handle 2-room flat type
        System.out.println("Are there 2-room flat types in this project (Y/N)? ");
        String input2;
        do {
            input2 = scanner.next().toUpperCase();
            if (!InputValidator.validateYesNo(input2)) {
                System.out.println("Please enter Y or N.");
            }
        } while (!InputValidator.validateYesNo(input2));
        if (input2.equals("Y")) {
            userInput.put("2-room", true);

            // Get total units
            System.out.println("Enter total number of units: ");
            String totalUnitsString;
            int totalUnits = 0;
            boolean validTotal = false;
            do {
                totalUnitsString = scanner.next();
                if (InputValidator.validateIntRange(totalUnitsString, 1, null)) {
                    totalUnits = Integer.parseInt(totalUnitsString);
                    validTotal = true;
                } else {
                    System.out.println("Invalid number format. Must be a positive number.");
                }
            } while (!validTotal);
            userInput.put("2-room totalUnits", totalUnits);

            // Get available units
            System.out.println("Enter number of available units: ");
            String sAvailableUnits;
            int availableUnits = 0;
            boolean validAvail = false;
            do {
                sAvailableUnits = scanner.next();
                if (InputValidator.validateIntRange(sAvailableUnits, 0, null)) {
                    availableUnits = Integer.parseInt(sAvailableUnits);
                    if (InputValidator.validateAvailableUnits(availableUnits, totalUnits)) {
                        validAvail = true;
                    } else {
                        System.out.println("Invalid input! Number of available units cannot be more than total number of units.");
                    }
                } else {
                    System.out.println("Invalid number format. Must be a non-negative number.");
                }
            } while (!validAvail);
            userInput.put("2-room availableUnits", availableUnits);

            // Get price per unit
            System.out.println("Enter price per unit: ");
            String sPrice;
            double price = 0;
            boolean validPrice = false;
            do {
                sPrice = scanner.next();
                if (InputValidator.validatePositiveDouble(sPrice)) {
                    price = Double.parseDouble(sPrice);
                    validPrice = true;
                } else {
                    System.out.println("Invalid number format. Price must be a positive number.");
                }
            } while (!validPrice);
            userInput.put("2-room price", price);
        } else {
            userInput.put("2-room", null);
        }

        // Handle 3-room flat type
        System.out.println("Are there 3-room flat types in this project (Y/N)? ");
        String input3;
        do {
            input3 = scanner.next().toUpperCase();
            if (!InputValidator.validateYesNo(input3)) {
                System.out.println("Please enter Y or N.");
            }
        } while (!InputValidator.validateYesNo(input3));

        if (input3.equals("Y")) {
            userInput.put("3-room", "3-room");

            // Get total units
            System.out.println("Enter total number of units: ");
            String sTotalUnits;
            int totalUnits = 0;
            boolean validTotal = false;
            do {
                sTotalUnits = scanner.next();
                if (InputValidator.validateIntRange(sTotalUnits, 1, null)) {
                    totalUnits = Integer.parseInt(sTotalUnits);
                    validTotal = true;
                } else {
                    System.out.println("Invalid number format. Must be a positive number.");
                }
            } while (!validTotal);
            userInput.put("3-room totalUnits", totalUnits);

            // Get available units
            System.out.println("Enter number of available units: ");
            String sAvailableUnits;
            int availableUnits = 0;
            boolean validAvail = false;
            do {
                sAvailableUnits = scanner.next();
                if (InputValidator.validateIntRange(sAvailableUnits, 0, null)) {
                    availableUnits = Integer.parseInt(sAvailableUnits);
                    if (InputValidator.validateAvailableUnits(availableUnits, totalUnits)) {
                        validAvail = true;
                    } else {
                        System.out.println("Invalid input! Number of available units cannot be more than total number of units.");
                    }
                } else {
                    System.out.println("Invalid number format. Must be a non-negative number.");
                }
            } while (!validAvail);
            userInput.put("3-room availableUnits", availableUnits);

            // Get price per unit
            System.out.println("Enter price per unit: ");
            String sPrice;
            double price = 0;
            boolean validPrice = false;
            do {
                sPrice = scanner.next();
                if (InputValidator.validatePositiveDouble(sPrice)) {
                    price = Double.parseDouble(sPrice);
                    validPrice = true;
                } else {
                    System.out.println("Invalid number format. Price must be a positive number.");
                }
            } while (!validPrice);
            userInput.put("3-room price", price);
        } else {
            userInput.put("3-room", null);
        }

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
