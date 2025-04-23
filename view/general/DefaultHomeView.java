
package view.general;


import project.Project;
import session.Session;
import view.MenuView;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class DefaultHomeView extends MenuView {
    private int userInput;
    private List<Project> btoProject = new ArrayList<>();

    @Override
    public void show() {
        System.out.println("Welcome!");
        System.out.println("1) Apply for BTO Project");
        System.out.println("2) View ALL projects");
        System.out.println("3) View Applied BTO Projects");
        System.out.println("4) Book Flat");
        System.out.println("5) Request Withdrawal");
        System.out.println("6) Enquiry");


        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // BTOApplicationView
                    break;
                case 2:
                    // ProjectListingView
                    Session.getSession().setCurrentView(new ProjectListingView());
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
                
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
