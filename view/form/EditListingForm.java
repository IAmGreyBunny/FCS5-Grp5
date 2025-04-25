package view.form;

import project.Project;
import project.ProjectController;
import project.ProjectRepository;
import project.UnitType;
import session.Session;
import validator.InputValidator;
import view.IFormView;
import view.HomeViewFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A form view that handles the process of editing an existing project listing.
 * This class implements IFormView and interacts with ProjectController to validate and update the project listing.
 */

public class EditListingForm implements IFormView {
    /**
     * @param userInput userInput is a HashMap that contains user inputs for editing the project listing.
     * @param oldProject oldProject contains the information of the project to be edited
     */
    HashMap<String, Object> userInput = new HashMap<>();
    private Project oldProject;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructor to initialize EditListingForm with the existing project.
     * @param oldProject oldProject is the existing project to be edited.
     */
    public EditListingForm(Project oldProject) {
        this.oldProject = oldProject;
    }

    /**
     * This method prompts the user to input details for editing the project listing.
     * It validates the inputs using InputValidator functions and updates the current view with EditUnitForm or HomeViewFactory.
     */
    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        Project project;
        String name = "";
        String neighbourhood = "";
        LocalDate openingDate = null;
        LocalDate closingDate = null;
        int officerSlots = -1;
        boolean visibility = oldProject.getVisibility();

        System.out.println("--- Edit Listing ---");
        System.out.println("Leave field empty to keep current values.");

        /**
         * @param name name is the name of the project
         */
        System.out.println("Current name - " + oldProject.getProjectName());
        System.out.println("Name: ");
        name = scanner.next();

        /**
         * @param neighbourhood neighbourhood is the neighbourhood of the project
         */
        System.out.println("Current neighbourhood - " + oldProject.getNeighbourhood());
        System.out.println("Neighbourhood: ");
        neighbourhood = scanner.next();

        /**
         * @param openingDate openingDate is the application opening date of the project
         * @param sOpening sOpening gets the user's opening date input in String to check its validity before converting to LocalDate format
         * @param validOpening validOpening checks if the format of date input is correct.
         */
        System.out.println("Current opening date - " + oldProject.getApplicationOpeningDate());
        System.out.println("Application Opening Date: ");
        String sOpening;
        do {
            sOpening = scanner.next();
            if (!sOpening.isEmpty()) {
                if (InputValidator.validateDate(sOpening, "dd/MM/yyyy")) {
                    openingDate = LocalDate.parse(sOpening, formatter);
                } else {
                    System.out.println("Invalid date format! Enter date in dd/MM/yyyy format.");
                }
            }
        } while (!sOpening.isEmpty() && !InputValidator.validateDate(sOpening, "dd/MM/yyyy"));

        /**
         * @param closingDate closingDate is the application closing date of the project
         * @param sClosing sClosing gets the user's closing date input in String to check its validity before converting to LocalDate format
         * @param validClosing validClosing checks if the format of date input is correct.
         */

        System.out.println("Current closing date - " + oldProject.getApplicationClosingDate());
        System.out.println("Application Closing Date: ");
        String sClosing;
        do {
            sClosing = scanner.next();
            if (!sClosing.isEmpty()){
                if (InputValidator.validateDate(sClosing, "dd/MM/yyyy")){
                    closingDate = LocalDate.parse(sClosing, formatter);
                }
                else {
                    System.out.println("Invalid date format! Enter date in dd/MM/yyyy format.");
                }
            }
        } while (!sClosing.isEmpty() && !InputValidator.validateDate(sClosing, "dd/MM/yyyy"));

        /**
         * @param officerSlots officerSlots is the number of officer slots for the project
         * @param sSlots sSlots gets the user's integer input in String to check its validity before converting to Integer format
         * @param validSlots validSlots checks if the format of integer input is correct.
         */

        System.out.println("Current number of officer slots - " + oldProject.getOfficerSlots());
        System.out.println("Number of Officer Slots: ");
        String sSlots;
        do {
            sSlots = scanner.next();
            if (!sSlots.isEmpty()) {
                if (InputValidator.validateIntRange(sSlots, 1, 10)) {
                    officerSlots = Integer.parseInt(sSlots);
                }
                else {
                    System.out.println("Invalid number format! Enter an integer between 1 and 10");
                }
            }
        } while (!sSlots.isEmpty() && !InputValidator.validateIntRange(sSlots, 1, 10));

        /**
         * @param visibility visibility is the visibility status of the project
         * @param sVisibility sVisibility gets the user's visibility input in String to check its validity before converting to boolean format
         */

        System.out.println("Current visibility - " + (oldProject.getVisibility()?"on":"off"));
        System.out.println("Visibility: ");
        String sVisibility;
        do {
            sVisibility = scanner.next().toUpperCase();
            if (InputValidator.validateYesNo(sVisibility)) {
                visibility = sVisibility.equalsIgnoreCase("Y");
            }
        } while (!InputValidator.validateYesNo(sVisibility));


        /**
         * puts the respective value and key of the user's inputs into the UserInput
         * creates the new listing and add it to the database
         */
        userInput.put("name", name);
        userInput.put("neighbourhood", neighbourhood);
        userInput.put("openingDate", openingDate);
        userInput.put("closingDate", closingDate);
        userInput.put("officerSlots", officerSlots);
        userInput.put("visibility", visibility);

        ProjectController.editListingWithUserInput(oldProject, this.getUserInput());


        ArrayList<UnitType> unitTypes = ProjectRepository.getUnitTypesByProjectId(oldProject.getProjectId());
        System.out.println("List of units: ");
        unitTypes.stream()
                .map(unit -> "ID: " + unit.getUnitTypeId() + " | Name: " + unit.getName())
                .forEach(System.out::println);
        System.out.println("Enter unit ID: ");
        String input;
        boolean validUnit = false;
        int unitId=-1;
        UnitType target = null;

        /**
         * Prompts the user to enter a unit ID and validates the input.
         * @param input input is the user's input for the unit ID.
         * @param validUnit validUnit checks if the entered unit ID is valid.
         * @param unitId unitId is the ID of the selected unit.
         * @param target target is the selected UnitType object.
         */
        do {
            input = scanner.next();
            if (!input.isEmpty()) {
                if (InputValidator.validateIntRange(input, 0, null)) {
                    unitId = Integer.parseInt(input);
                    for (UnitType unit : unitTypes) {
                        if (unit.getUnitTypeId() == unitId) {
                            target = unit;
                            validUnit = true;
                        }
                    }
                } else {
                    System.out.println("Invalid input!");
                }
            }
            else {
                validUnit = true;
            }
        } while (!input.isEmpty() && !validUnit);

        /**
         * Sets the current view to EditUnitForm if a valid unit is selected, otherwise sets it to HomeViewFactory.
         * @param target target is the selected UnitType object.
         */
        if (target != null) {
            Session.getSession().setCurrentView(new EditUnitForm(target, this.getUserInput()));
        }
        else {
            Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
        }

    }

    /**
     * Retrieves the user input map containing details for editing the project listing.
     * @return userInput which is a HashMap with keys "name", "neighbourhood", "openingDate", "closingDate", "officerSlots", and "visibility" representing the user's inputs.
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