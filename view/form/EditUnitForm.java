package view.form;

import project.Project;
import project.UnitType;
import project.ProjectController;
import validator.InputValidator;
import view.FormView;

import java.util.HashMap;
import java.util.Scanner;

public class EditUnitForm extends FormView {
    HashMap userInput = new HashMap<>();
    private UnitType oldUnitType;
    public EditUnitForm(Project oldProject) {
        this.oldUnitType = oldUnitType;
    }
    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        String name = "";
        int totalUnits = -1;
        int availableUnits = -1;
        double pricePerUnit = -1;

        System.out.println("--- Edit Unit Type ---");

        // TODO need to get user input for the unit to be edited (id?)

        System.out.println("Leave field empty to keep current values.");

        System.out.println("Current name - " + oldUnitType.getName());
        System.out.println("Name: ");
        name = scanner.next();

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

        // TODO not sure about this part cause we need to ensure that available Units < total units
//        System.out.println("Current available number of units - " + oldUnitType.getAvailable());
//        System.out.println("Available Units: ");
//        String sAvailableUnits;
//        do {
//            sAvailableUnits = scanner.next();
//            if (!sAvailableUnits.isEmpty()) {
//                if (InputValidator.validateIntRange(sAvailableUnits, 0, )) {
//                    availableUnits = Integer.parseInt(sAvailableUnits);
//                } else {
//                    System.out.println("Invalid number format! Enter an integer.");
//                }
//            }
//        } while (!sAvailableUnits.isEmpty() && !InputValidator.validateIntRange(sAvailableUnits));

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

        userInput.put("name", name);
        userInput.put("total", totalUnits);
        userInput.put("available", availableUnits);
        userInput.put("pricePerUnit", pricePerUnit);
        ProjectController.editUnitTypeWithUserInput(oldUnitType, this.getUserInput());
    }
    @Override
    public HashMap getUserInput() {
        return userInput;
    }
    @Override
    public void show() {
        prompt();
    }
}



