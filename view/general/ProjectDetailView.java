package view.general;

import project.Project;
import project.ProjectController;
import project.UnitType;
import session.Session;
import view.MenuView;
import view.View;

import java.awt.*;
import java.util.Scanner;

public class ProjectDetailView extends MenuView {

    View prevView;
    Project project;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    ProjectDetailView(View prevView,Project project)
    {
        this.prevView = prevView;
        if(project != null)
        {
            this.project = project;
        }else
        {
            System.out.println("Invalid Project");
            Session.getSession().setCurrentView(prevView);
        }
    }


    @Override
    public void show() {
        for(UnitType unitType : ProjectController.getUnitTypesByProject(project.getProjectId()))
        {
            System.out.println(unitType.toString());
        }

        System.out.println("Enter anything to return");
        scanner.next();
        Session.getSession().setCurrentView(prevView);
    }
}
