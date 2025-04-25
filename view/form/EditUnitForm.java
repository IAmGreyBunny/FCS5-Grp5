package view.form;

import project.Project;
import project.UnitType;
import project.ProjectController;
import session.Session;
import validator.InputValidator;
import view.IFormView;
import view.HomeViewFactory;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A form view that handles the process of editing an existing unit type.
 * This class implements IFormView and interacts with ProjectController to validate and update the unit type.
 */

public class EditUnitForm implements IFormView {
    /**
     * @param userInput userInput is a HashMap that contains user inputs for editing the unit type.
     */
    HashMap userInput = new HashMap<>();
    private UnitType oldUnitType;

    /**
     * Constructor to initialize EditUnitForm with the existing unit type and user inputs.
     * @param oldUnitType oldUnitType is the existing unit type to be edited.
     * @param userInput userInput is a HashMap that contains user inputs for editing the unit type.
     */
    public EditUnitForm(UnitType oldUnitType, HashMap<String, Object> userInput) {
        this.oldUnitType = oldUnitType;
        this.userInput = userInput;
    }

    /**
     * This method prompts the user to input details for editing the unit type.
     * It validates the inputs using InputValidator functions and updates the current view with HomeViewFactory.
     */
    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        String name = "";
        int totalUnits = -1;
        int availableUnits = -1;
        double pricePerUnit = -1;

        System.out.println("--- Edit Unit Type ---");
        System.out.println("Leave field empty to keep current values.");

        /**
         * @param name name is the name of the unit type
         */
        System.out.println("Current name - " + oldUnitType.getName());
        System.out.println("Name: ");
        name = scanner.next();

        /**
         * @param totalUnits totalUnits is the total number of units for the unit type
         * @param sTotalUnits sTotalUnits gets the user's total units input in String to check its validity before converting to Integer format
         * @param validTotal validTotal checks if the format of integer input is correct.
         */
        System.out.println("Current total number of units - " + oldUnitType.getTotal());
        System.out.println("Total Units: ");
        String sTotalUnits;
        do {
            sTotalUnits = scanner.next();
            if (!sTotalUnits.isEmpty()) {
                if (InputValidator.validateIntRange(sTotalUnits, 0, null)) {
                    totalUnits = Integer.parseInt(sTotalUnits);
                } else {
                    System.out.println("Invalid number format! Enter an integer.");
                }
            }
        } while (!sTotalUnits.isEmpty() && !InputValidator.validateIntRange(sTotalUnits, 0, null));

        /**
         * @param availableUnits availableUnits is the available number of units for the unit type
         * @param sAvailableUnits sAvailableUnits gets the user's available units input in String to check its validity before converting to Integer format
         * @param validAvailable validAvailable checks if the format of integer input is correct.
         */
        System.out.println("Current available number of units - " + oldUnitType.getAvailable());
        System.out.println("Available Units: ");
        String sAvailableUnits;
        do {
            sAvailableUnits = scanner.next();
            if (!sAvailableUnits.isEmpty()) {
                if (InputValidator.validateIntRange(sAvailableUnits, 0, null)) {
                    availableUnits = Integer.parseInt(sAvailableUnits);
                } else {
                    System.out.println("Invalid number format! Enter an integer.");
                }
            }
        } while (!sAvailableUnits.isEmpty() && !InputValidator.validateIntRange(sAvailableUnits, 0, null));

        /**
         * @param pricePerUnit pricePerUnit is the price per unit for the unit type
         * @param sPricePerUnit sPricePerUnit gets the user's price per unit input in String to check its validity before converting to Double format
         * @param validPrice validPrice checks if the format of double input is correct.
         */
        System.out.println("Current price per unit - " + oldUnitType.getPricePerUnit());
        System.out.println("Price Per Unit: ");
        String sPricePerUnit;
        do {
            sPricePerUnit = scanner.next();
            if (!sPricePerUnit.isEmpty()) {
                if (InputValidator.validatePositiveDouble(sPricePerUnit)) {
                    pricePerUnit = Double.parseDouble(sPricePerUnit);
                } else {
                    System.out.println("Invalid number format! Enter a decimal number.");
                }
            }
        } while (!sPricePerUnit.isEmpty() && !InputValidator.validatePositiveDouble(sPricePerUnit));

        /**
         * puts the respective value and key of the user's inputs into the UserInput
         * creates the new listing and add it to the database
         */
        userInput.put("name", name);
        userInput.put("total", totalUnits);
        userInput.put("available", availableUnits);
        userInput.put("pricePerUnit", pricePerUnit);
        ProjectController.editUnitTypeWithUserInput(oldUnitType, this.getUserInput());

        Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
    }
    /**
     * Retrieves the user input map containing details for editing the unit type.
     * @return userInput which is a HashMap with keys "name", "total", "available", and "pricePerUnit" representing the user's inputs.
     */
    @Override
    public HashMap getUserInput() {
        return userInput;
    }
    @Override
    public void show() {
        prompt();
    }
}



