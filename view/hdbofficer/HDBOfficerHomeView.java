package view.hdbofficer;

import project.Project;
import session.Session;
import view.MenuView;
import view.general.DefaultHomeView;

import java.util.InputMismatchException;
import java.util.List;
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
                    // BTOApplicationView
                    break;
                case 2:
                    // ProjectListingView
                    break;
                case 3:
                    // MyApplicationView
                    break;
                case 4:
                    // BookFlatView
                    break;
                case 5:
                    // WithdrawalView
                    break;
                case 6:
                    // EnquiriesView
                    break;
                case 7:
                    // ProjectOfficerRegistrationView
                    break;
                case 8:
                    // OfficerProjectManagementView

                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
