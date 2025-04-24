package view.general;

import project.Project;
import project.ProjectController;
import session.Session;
import view.MenuView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProjectListingView extends MenuView {

    int userInput;

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Override
    public void show() {
        ArrayList<Project> listOfApplicableProjects = ProjectController.getApplicableProject(Session.getSession().getCurrentUser());

        System.out.println("--- All Applicable Projects ---");
        for(Project project : listOfApplicableProjects)
        {
            System.out.println(project.toString());
        }
        userInput = scanner.nextInt();
        switch (userInput)
        {
            case 1:
                break;
            default:
                break;
        }
    }
}
