package view.form;

import project.Project;
import project.ProjectController;
import session.Session;
import validator.InputValidator;
import view.FormView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class EditListingForm extends FormView {
    HashMap<String, Object> userInput = new HashMap<>();
    private Project oldProject;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EditListingForm(Project oldProject) {
        this.oldProject = oldProject;
    }

    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        String name = "";
        String neighbourhood = "";
        LocalDate openingDate = null;
        LocalDate closingDate = null;
        int officerSlots = -1;
        boolean visibility = oldProject.getVisibility();

        System.out.println("--- Edit Listing ---");
        System.out.println("Leave field empty to keep current values.");

        System.out.println("Current name - " + oldProject.getProjectName());
        System.out.println("Name: ");
        name = scanner.next();

        System.out.println("Current neighbourhood - " + oldProject.getNeighbourhood());
        System.out.println("Neighbourhood: ");
        neighbourhood = scanner.next();

        System.out.println("Current opening date - " + oldProject.getApplicationOpeningDate());
        System.out.println("Application Opening Date: ");
        String sOpening;
        do {
            sOpening = scanner.next();
            if (!sOpening.isEmpty()){
                if (InputValidator.validateDate(sOpening, "dd/MM/yyyy")){
                    openingDate = LocalDate.parse(sOpening, formatter);
                }
                else {
                    System.out.println("Invalid date format! Enter date in dd/MM/yyyy format.");
                }
            }
        } while (!sOpening.isEmpty() && !InputValidator.validateDate(sOpening, "dd/MM/yyyy"));

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

        System.out.println("Current visibility - " + (oldProject.getVisibility()?"on":"off"));
        System.out.println("Visibility: ");
        String sVisibility;
        do {
            sVisibility = scanner.next().toUpperCase();
            if (InputValidator.validateYesNo(sVisibility)) {
                visibility = sVisibility.equalsIgnoreCase("Y");
            }
        } while (!InputValidator.validateYesNo(sVisibility));


        userInput.put("name", name);
        userInput.put("neighbourhood", neighbourhood);
        userInput.put("openingDate", openingDate);
        userInput.put("closingDate", closingDate);
        userInput.put("officerSlots", officerSlots);
        userInput.put("visibility", visibility);

        ProjectController.editListingWithUserInput(oldProject, this.getUserInput());
        //Session.getSession().setCurrentView(new EditUnitForm(oldProject));


    }

    @Override
    public <T> T getUserInput() {
        return null;
    }

    @Override
    public void show() {

    }
}