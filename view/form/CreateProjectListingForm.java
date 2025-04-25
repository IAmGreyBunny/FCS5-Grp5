package view.form;

import project.Project;
import project.ProjectController;
import session.Session;
import view.IFormView;
import validator.InputValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A form view that handles the process of creating a new project listing.
 * This class implements IFormView and interacts with ProjectController to validate and create the project listing.
 */

public class CreateProjectListingForm implements IFormView {

    /**
     * @param userInput userInput is a HashMap that contains user inputs for creating a new project listing.
     */
    HashMap<String, Object> userInput = new HashMap<>();

    /**
     * This method prompts the user to input details for the new project listing.
     * It validates the inputs using InputValidator functions and updates the current view with CreateUnitTypeForm.
     */
    @Override
    public void prompt() {

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String name;
        String neighbourhood;
        LocalDate openingDate;
        LocalDate closingDate;
        int officerSlots;

        System.out.println("--- Create BTO Project ---");

        /**
         * @param name name is the name of the new project
         */
        System.out.println("Enter name of new project: ");
        name = scanner.next();

        /**
         * @param neighbourhood neighbourhood is the neighbourhood of the new project
         */
        System.out.println("Enter neighbourhood of this project: ");
        neighbourhood = scanner.next();

        /**
         * @param openingDate openingDate is the application opening date of the new project
         * @param sOpeningDate sOpeningDate gets the user's opening date input in String  to check its validity before converting to LocalDate format
         * @param validOpening validOpening checks if the format of date input is correct.
         */
        System.out.println("Enter application opening date: ");
        boolean validOpening;
        String sOpeningDate;
        do {
            sOpeningDate = scanner.next();
            validOpening = InputValidator.validateDate(sOpeningDate, "dd/MM/yyyy");
            if (!validOpening) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        } while (!validOpening);
        openingDate = LocalDate.parse(sOpeningDate, formatter);

        /**
         * @param closingDate closingDate is the application closing date of the new project
         * @param sClosingDate sClosingDate gets the user's closing date input in String  to check its validity before converting to LocalDate format
         * @param validClosing validOpening checks if the format of date input is correct.
         */
        System.out.println("Enter application closing date: ");
        boolean validClosing;
        String sClosingDate;
        do {
            sClosingDate = scanner.next();
            validClosing = InputValidator.validateDate(sClosingDate, "dd/MM/yyyy");
            if (!validClosing) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        } while (!validClosing);
        closingDate = LocalDate.parse(sClosingDate, formatter);

        /**
         * @param officerSlots officerSlots is the number of officer slots for the new project
         * @param sSlots sSlots gets the user's integer input in String  to check its validity before converting to Integer format
         * @param validSlots validSlots checks if the format of integer input is correct.
         */
        System.out.println("Enter number of officer slots: ");
        boolean validSlots;
        String sSlots;
        do {
            sSlots = scanner.next();
            validSlots = InputValidator.validateIntRange(sSlots, 1, 10);
            if (!validSlots) {
                System.out.println("Invalid input. Please enter a value between 1 and 10.");
            }
        } while (!validSlots);
        officerSlots = Integer.parseInt(sSlots);


        /**
         * puts the respective value and key of the user's inputs into the UserInput
         * creates the new listing and add it to the database
         */
        userInput.put("name", name);
        userInput.put("neighbourhood", neighbourhood);
        userInput.put("openingDate", openingDate);
        userInput.put("closingDate", closingDate);
        userInput.put("officerSlots", officerSlots);

        Project project = ProjectController.createListingWithUserInput(this.getUserInput());

        Session.getSession().setCurrentView(new CreateUnitTypeForm(project.getProjectId()));
    }

    /**
     * Retrieves the user input map containing details for the new project listing.
     * @return userInput which is a HashMap with keys "name", "neighbourhood", "openingDate", "closingDate", and "officerSlots" representing the user's inputs.
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
