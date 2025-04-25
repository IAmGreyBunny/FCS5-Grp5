package view.general;

import project.Project;
import project.ProjectController;
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
        //Controller to apply
    }
}
