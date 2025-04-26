package view.general.applicants;

import application.BTOApplicationController;
import project.Project;
import project.ProjectController;
import project.UnitType;
import session.Session;
import view.IMenuView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A menu view that displays all applicable projects for the user to apply.
 * This class implements IMenuView and interacts with ProjectController to retrieve and display the projects.
 */
public class BTOApplicationView implements IMenuView {

    /**
     * @param userInput userInput is the user's input for selecting a project to apply.
     */
    int userInput;

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    /**
     * This method displays all applicable projects for the user to apply.
     * It retrieves the projects from ProjectController and prompts the user to select a project by entering its ID.
     */
    @Override
    public void show() {
        ArrayList<Project> listOfApplicableProjects = ProjectController.getApplicableProject(Session.getSession().getCurrentUser());

        System.out.println("--- All Applicable Projects ---");
        for(Project project : listOfApplicableProjects)
        {
            System.out.println(project.getProjectId() + " | " + project.getProjectName());
        }

        /**
         * @param userInput userInput is the user's input for selecting a project to apply.
         */
        System.out.print("Enter Id to apply:");
        userInput = scanner.nextInt();

        ArrayList<UnitType> applicableUnitTypes = ProjectController.getApplicableUnitTypes(userInput);
        System.out.println("--- Applicable Unit Types ---");
        for (UnitType unitType : applicableUnitTypes) {
            System.out.println(unitType.getUnitTypeId() + " | " + unitType.getName() + " | Available: " + unitType.getAvailable() + " | Price per unit: " + unitType.getPricePerUnit());
        }
        System.out.print("Enter Unit Type Id to apply:");
        int unitTypeId = scanner.nextInt();
        UnitType target = null;
        for (UnitType unitType : applicableUnitTypes) {
            if (unitType.getUnitTypeId() == unitTypeId) {
                target = unitType;
            }
        }

        BTOApplicationController controller = new BTOApplicationController();
        boolean success = controller.applyToBTO(String.valueOf(Session.getSession().getCurrentUser().getUid()), String.valueOf(userInput), target);


        if (success) {
            System.out.println("Application submitted successfully.");
        } else {
            System.out.println("Failed to submit application.");
        }
    }
}
