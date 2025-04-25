package view.hdbmanager.applications;

import session.Session;
import view.HomeViewFactory;
import view.hdbmanager.HDBManagerHomeView;
import view.hdbmanager.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageOfficerApplicationView extends ManageApplicationView {
    private int userInput;

    @Override
    public void show() {
        System.out.println("==== BTO Officer Applications ====");
        System.out.println("1. Manage Requests");
        System.out.println("2. Print Officer Registration");
        System.out.println("3. Return");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to approve and reject requests
                    break;
                case 2:
                    // code to print officers (approved and pending)
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
