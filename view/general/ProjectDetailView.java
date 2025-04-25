package view.general;

import project.Project;
import project.ProjectController;
import project.UnitType;
import session.Session;
import view.IMenuView;
import view.IView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A menu view that displays the details of a specific project.
 * This class implements IMenuView and provides options for the user to view unit types and return to the previous view.
 */

public class ProjectDetailView implements IMenuView {

    /**
     * @param prevView prevView is the previous view to return to.
     * @param project project is the project whose details are being displayed.
     */
    IView prevView;
    Project project;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    /**
     * Constructor to initialize ProjectDetailView with the previous view and the project.
     * @param prevView prevView is the previous view to return to.
     * @param project project is the project whose details are being displayed.
     */
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


    /**
     * This method displays the details of the project, including its unit types.
     * It retrieves the unit types from ProjectController and prompts the user to return to the previous view.
     */
    @Override
    public void show() {
        ArrayList<UnitType> listOfUnitType = ProjectController.getApplicableUnitTypes(project.getProjectId());

        /**
         * Retrieves the list of applicable unit types for the project and displays them.
         * @param listOfUnitType listOfUnitType is the list of applicable unit types for the project.
         */
        if(!listOfUnitType.isEmpty()){
            for(UnitType unitType : listOfUnitType)
            {
                System.out.println(unitType.toString());
            }
        }else{
            System.out.println("No available units");
        }

        /**
         * Prompts the user to enter anything to return to the previous view.
         */

        System.out.println("Enter anything to return");
        scanner.next();
        Session.getSession().setCurrentView(prevView);
    }
}
