
package view.general;


import project.Project;
import session.Session;
import view.IMenuView;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * A menu view that displays the default home view for the user.
 * This class implements IMenuView and provides options for the user to navigate to different views.
 */

public class DefaultHomeView implements IMenuView {
    /**
     * @param userInput userInput is the user's input for selecting an option from the menu.
     * @param btoProject btoProject creates a new list containing the projects:Project
     */
    private int userInput;
    private List<Project> btoProject = new ArrayList<>();

    /**
     * This method displays the default home view menu and prompts the user to select an option.
     * It handles the user's input and navigates to the corresponding view based on the selected option.
     */
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
            /**
             * @param userInput userInput is the user's input for selecting an option from the menu.
             */

            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // MyProfileView
                    Session.getSession().setCurrentView(new MyProfileView());
                case 2:
                    // BTOApplicationView
                    Session.getSession().setCurrentView(new BTOApplicationView());
                    break;
                case 3:
                    // ProjectListingView
                    Session.getSession().setCurrentView(new ProjectListingView());
                    break;
                case 4:
                    // MyApplicationView
                    Session.getSession().setCurrentView(new MyApplicationView());
                    break;
                case 5:
                    // BookFlatView
                    Session.getSession().setCurrentView(new BookFlatView());
                    break;
                case 6:
                    // WithdrawalView TODO not sure how the withdrawal works
                    //Session.getSession().setCurrentView(new WithdrawalView());
                    break;
                case 7:
                    // ApplicationEnquiryView
                    Session.getSession().setCurrentView(new ApplicantEnquiryView());
                    break;
                
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
