package view.form;

import project.ProjectController;
import session.Session;
import view.IFormView;
import validator.InputValidator;
import view.hdbmanager.ManagerProjectManagementView;


import java.util.HashMap;
import java.util.Scanner;

/**
 * A form view that handles the process of creating a new unit type for a project.
 * This class implements IFormView and interacts with ProjectController to validate and create the unit type.
 */

public class CreateUnitTypeForm implements IFormView {

    /**
     * @param projectId projectId is the ID of the project for which the unit type is being created.
     */
    int projectId;

    /**
     * Constructor to initialize CreateUnitTypeForm with the project ID.
     * @param projectId projectId is the ID of the project for which the unit type is being created.
     */
    CreateUnitTypeForm(int projectId){
        this.projectId = projectId;
    }

    /**
     * @param userInput userInput is a HashMap that contains user inputs for creating a new unit type.
     */
    HashMap<String, Object> userInput = new HashMap<>();
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    /**
     * This method prompts the user to input details for the new unit type.
     * It validates the inputs using InputValidator functions and updates the current view with ManagerProjectManagementView.
     */
    @Override
    public void prompt() {

        String name;
        int available;
        int total;
        double pricePerUnit;
        boolean newUnit;     // to create more than 1 unit type


        do {
            /**
             * @param name name is the name of the unit type
             */
            System.out.println("Enter name of unit type: ");
            name = scanner.next();

            /**
             * @param total total is the total number of units for the unit type
             * @param sTotal sTotal gets the user's total units input in String to check its validity before converting to Integer format
             * @param validTotal validTotal checks if the format of integer input is correct.
             */
            System.out.println("Enter total number of units: ");
            String sTotal;
            boolean validTotal;
            do {
                sTotal = scanner.next();
                validTotal = InputValidator.validateIntRange(sTotal, 0, null);
                if (!validTotal) {
                    System.out.println("Invalid input. Please enter a non-negative integer.");
                }
            } while (!validTotal);
            total = Integer.parseInt(sTotal);


            /**
             * @param available available is the available number of units for the unit type
             * @param sAvail sAvail gets the user's available units input in String to check its validity before converting to Integer format
             * @param validAvail validAvail checks if the format of integer input is correct.
             */
            System.out.println("Enter available number of units: ");
            String sAvail;
            boolean validAvail;
            do {
                sAvail = scanner.next();
                validAvail = InputValidator.validateIntRange(sAvail, 0, total);
                if (!validAvail) {
                    System.out.println("Invalid input. Please enter a value between 0 and " + total + ".");
                }
            } while (!validAvail);
            available = Integer.parseInt(sAvail);

            /**
             * @param pricePerUnit pricePerUnit is the price per unit for the unit type
             * @param sPrice sPrice gets the user's price per unit input in String to check its validity before converting to Double format
             * @param validPrice validPrice checks if the format of double input is correct.
             */
            System.out.println("Enter price per unit: ");
            String sPrice;
            boolean validPrice;
            do {
                sPrice = scanner.next();
                validPrice = InputValidator.validatePositiveDouble(sPrice);
                if (!validPrice) {
                    System.out.println("Invalid input. Please enter a positive double value.");
                }
            } while (!validPrice);
            pricePerUnit = Double.parseDouble(sPrice);

            /**
             * puts the respective value and key of the user's inputs into the UserInput
             * creates the new listing and add it to the database
             */
            userInput.put("name", name);
            userInput.put("available", available);
            userInput.put("total", total);
            userInput.put("pricePerUnit", pricePerUnit);

            ProjectController.createUnitTypeWithUserInput(this.projectId, this.getUserInput());

            /**
             * Prompts user to add another unit type.
             * @param newUnit newUnit indicates whether to create another unit type (Y/N).
             */
            System.out.println("Add another unit type (Y/N)?");
            String input;
            do {
                input = scanner.next().toUpperCase();
                if (!InputValidator.validateYesNo(input)) {
                    System.out.println("Enter either 'Y' or 'N'.");
                }
            } while(!InputValidator.validateYesNo(input));

            newUnit = input.equalsIgnoreCase("Y");

        } while(newUnit);

        Session.getSession().setCurrentView(new ManagerProjectManagementView());
    }

    /**
     * Retrieves the user input map containing details for the new unit type.
     * @return userInput which is a HashMap with keys "name", "available", "total", and "pricePerUnit" representing the user's inputs.
     */
    @Override
    public HashMap<String,Object> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }
}
