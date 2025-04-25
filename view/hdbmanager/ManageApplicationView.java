package view.hdbmanager;

import session.Session;
import view.HomeViewFactory;
import view.hdbmanager.applications.*;
import view.IMenuView;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents the view for managing all BTO Project and Officer applications.
 * The user can also choose to return to the home view.
 */
public class ManageApplicationView implements IMenuView {
    private int userInput;

    /**
     * Displays the manage applications menu to the HDB Manager.
     * Based on the user's choice, the current view is set to the respective functionality.
     */
    @Override
    public void show() {
        System.out.println("==== Manage Applications ====");
        System.out.println("1. BTO Project");
        System.out.println("2. BTO Officers");
        System.out.println("3. Return");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    Session.getSession().setCurrentView(new ManageProjectApplicationView());
                    break;
                case 2:
                    Session.getSession().setCurrentView(new ManageOfficerApplicationView());
                    break;
                case 3:
                    // Returns Home
                    Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }

    
}
