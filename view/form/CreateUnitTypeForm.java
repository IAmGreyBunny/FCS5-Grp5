package view.form;

import project.ProjectController;
import session.Session;
import view.IFormView;
import validator.InputValidator;
import view.hdbmanager.ManagerProjectManagementView;
//import view.hdbmanager.ManagerProjectManagementView;

import java.util.HashMap;
import java.util.Scanner;

public class CreateUnitTypeForm implements IFormView {

    int projectId;

    CreateUnitTypeForm(int projectId){
        this.projectId = projectId;
    }

    HashMap<String, Object> userInput = new HashMap<>();
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Override
    public void prompt() {
        // get all the user input into variables(use the InputValidator functions to validate)
        // except projectId, ProjectRepo.findMaxUnitTypeId
        // put all the user inputs into userInput based on the keys found in controller(exact string match)
        // createUnitTypeWithUserInput(HashMap<String, Object> userInput)
        // update currentView with new CreateUnitTypeForm()

        String name;
        int available;
        int total;
        double pricePerUnit;
        boolean newUnit;     // to create more than 1 unit type


        do {
            System.out.println("Enter name of unit type: ");
            name = scanner.next();

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

            userInput.put("name", name);
            userInput.put("available", available);
            userInput.put("total", total);
            userInput.put("pricePerUnit", pricePerUnit);

            ProjectController.createUnitTypeWithUserInput(this.projectId, this.getUserInput());

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

    @Override
    public HashMap<String,Object> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }
}
