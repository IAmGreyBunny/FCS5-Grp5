package view.general;

import project.Project;
import project.ProjectController;
import session.Session;
import view.IMenuView;

import java.util.ArrayList;
import java.util.Scanner;

public class BTOApplicationView implements IMenuView {

    int userInput;

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Override
    public void show() {
        ArrayList<Project> listOfApplicableProjects = ProjectController.getApplicableProject(Session.getSession().getCurrentUser());

        System.out.println("--- All Applicable Projects ---");
        for(Project project : listOfApplicableProjects)
        {
            System.out.println(project.getProjectId() + " | " + project.getProjectName());
        }

        System.out.print("Enter Id to apply:");
        userInput = scanner.nextInt();
        //Controller to apply
    }
}
