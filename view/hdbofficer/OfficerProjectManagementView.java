package view.hdbofficer;

import session.Session;
import view.HomeViewFactory;
import view.IMenuView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OfficerProjectManagementView implements IMenuView {
    private int userInput;

    @Override
    public void show() {
        System.out.println("======== BTO Project ========");
        System.out.println("1. View Details");
        System.out.println("2. View Enquiries");
        System.out.println("3. Flat Selection");
        System.out.println("4. Generate Flat Selection Receipt");
        System.out.println("5. Return");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to print project details
                    break;
                case 2:
                    // ProjectEnquiryView
                    break;
                case 3:
                    // FlatSelectionView
                    break;
                case 4:
                    // code to generate receipt
                    break;
                case 5:
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
