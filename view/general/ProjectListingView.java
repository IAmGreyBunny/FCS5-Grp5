package view.general;

import project.Project;
import project.ProjectController;
import session.Session;
import view.HomeViewFactory;
import view.IMenuView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A menu view that displays all applicable projects for the user.
 * This class implements IMenuView and interacts with ProjectController to retrieve and display the projects.
 */
public class ProjectListingView implements IMenuView {

    /**
     * @param userInput userInput is the user's input for selecting a project to view details.
     */
    String userInput;

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    /**
     * This method displays all applicable projects for the user.
     * It retrieves the projects from ProjectController and prompts the user to select a project by entering its ID.
     */
    @Override
    public void show() {
        ArrayList<Project> listOfApplicableProjects = ProjectController.getApplicableProject(Session.getSession().getCurrentUser());

        /**
         * Retrieves the list of applicable projects for the current user and displays them.
         * @param listOfApplicableProjects listOfApplicableProjects is the list of applicable projects for the current user.
         */

        System.out.println("--- All Applicable Projects ---");
        for(Project project : listOfApplicableProjects)
        {
            System.out.println(project.toString());
        }
        /**
         * Prompts the user to enter a project ID to view details or return to home.
         * @param userInput userInput is the user's input for selecting a project to view details or return to home.
         */

        System.out.println("Enter Project Id to view details (Empty to return to home)");
        try{
            userInput = scanner.nextLine();
            if(userInput.isEmpty())
            {
                // Returns home
                Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
            }else {
                // Move to ProjectDetailView
                int projectId = Integer.parseInt(userInput);
                Session.getSession().setCurrentView(new ProjectDetailView(this,ProjectController.getProjectById(projectId)));
            }
        }catch (Exception e)
        {
            System.out.println("Invalid Input");
        }

    }
}
