package view.general;

import project.Project;
import project.ProjectController;
import project.UnitType;
import session.Session;
import view.IMenuView;
import view.IView;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectDetailView implements IMenuView {

    IView prevView;
    Project project;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    ProjectDetailView(IView prevView, Project project)
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
        ArrayList<UnitType> listOfUnitType = ProjectController.getApplicableUnitTypes(project.getProjectId());

        if(!listOfUnitType.isEmpty()){
            for(UnitType unitType : listOfUnitType)
            {
                System.out.println(unitType.toString());
            }
        }else{
            System.out.println("No available units");
        }


        System.out.println("Enter anything to return");
        scanner.next();
        Session.getSession().setCurrentView(prevView);
    }
}
