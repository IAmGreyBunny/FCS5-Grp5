package view.hdbofficer;

import project.Project;
import session.Session;
import view.MenuView;
import view.general.DefaultHomeView;
import view.general.MyProfileView;
import view.general.ProjectListingView;
import view.hdbmanager.EnquiriesView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HDBOfficerHomeView extends MenuView {
    private int userInput;


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
        System.out.println("8) HDB Officer Registration");
        System.out.println("9) Manage Project");

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
                    Session.getSession().setCurrentView(new EnquiriesView());
                    break;
                case 8:
                    // ProjectOfficerRegistrationView
                    break;
                case 9:
                    // OfficerProjectManagementView
                    Session.getSession().setCurrentView(new OfficerProjectManagementView());

                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
