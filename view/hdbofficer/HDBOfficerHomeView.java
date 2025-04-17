package view.hdbofficer;

import session.Session;
import view.MenuView;
import view.general.BTOApplicationMenuView;
import view.hdbmanager.OfficerProjectView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HDBOfficerHomeView extends MenuView {
    private int userInput;


    @Override
    public void show() {
        System.out.println("Welcome!");
        System.out.println("1) Apply for BTO Project");
        System.out.println("2) HDB Officer Registration");
        System.out.println("2) Manage Project");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    Session.getSession().setCurrentView(new BTOApplicationMenuView());
                    break;
                case 2:
                    // code to apply to be an officer for a project
                    break;
                case 3:
                    // check if user is an officer for a project
                    if ( /* if officer is handling a project */ ) {
                        Session.getSession().setCurrentView(new OfficerProjectView());
                    }
                    else {
                        System.out.println("Not handling any BTO Projects!");
                    }

                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
