
package view.general;


import project.Project;
import session.Session;
import view.IMenuView;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class DefaultHomeView implements IMenuView {
    private int userInput;
    private List<Project> btoProject = new ArrayList<>();

    @Override
    public void show() {
        System.out.println("Welcome!");
        System.out.println("1) My Profile");
        System.out.println("2) Apply for BTO Project");
        System.out.println("3) View ALL projects");
        System.out.println("4) View Applied BTO Projects");
        System.out.println("5) Book Flat");
        System.out.println("6) Request Withdrawal");
        System.out.println("7) Enquiry");


        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // MyProfileView
                    Session.getSession().setCurrentView(new MyProfileView());
                case 2:
                    // BTOApplicationView
                    break;
                case 3:
                    // ProjectListingView
                    Session.getSession().setCurrentView(new ProjectListingView());
                    break;
                case 4:
                    // MyApplicationView
                    break;
                case 5:
                    // BookFlatView
                    break;
                case 6:
                    // WithdrawalView
                    break;
                case 7:
                    // EnquiriesView
                    break;
                
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
