package view.form;

import project.Project;
import project.ProjectController;
import project.ProjectRepository;
import session.Session;
import view.FormView;
import validator.InputValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class CreateProjectListingForm extends FormView {

    HashMap<String, Object> userInput = new HashMap<>();

    @Override
    public void prompt() {
        // get all the user input into variables(use the InputValidator functions to validate)
        // except projectId, ProjectRepo.findMax
        // put all the user inputs into userInput based on the keys found in controller(exact string match)
        // createListingWithUserInput(HashMap<String, Object> userInput)
        // update currentView with new CreateUnitTypeForm()

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String name;
        String neighbourhood;
        LocalDate openingDate;
        LocalDate closingDate;
        int officerSlots;

        System.out.println("--- Create BTO Project ---");

        System.out.println("Enter name of new project: ");
        name = scanner.next();

        System.out.println("Enter neighbourhood of this project: ");
        neighbourhood = scanner.next();

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


        userInput.put("name", name);
        userInput.put("neighbourhood", neighbourhood);
        userInput.put("openingDate", openingDate);
        userInput.put("closingDate", closingDate);
        userInput.put("officerSlots", officerSlots);

        Project project = ProjectController.createListingWithUserInput(this.getUserInput());

        Session.getSession().setCurrentView(new CreateUnitTypeForm(project.getProjectId()));
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
