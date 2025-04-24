package view.general;

import project.Project;
import project.ProjectController;
import session.Session;
import view.MenuView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProjectListingView extends MenuView {

    String userInput;

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Override
    public void show() {
        ArrayList<Project> listOfApplicableProjects = ProjectController.getApplicableProject(Session.getSession().getCurrentUser());

        System.out.println("--- All Applicable Projects ---");
        for(Project project : listOfApplicableProjects)
        {
            System.out.println(project.toString());
        }
        System.out.println("Enter Project Id to view details (Empty to return to home)");
        try{
            userInput = scanner.nextLine();
            if(userInput.isEmpty())
            {
                // Returns home
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
