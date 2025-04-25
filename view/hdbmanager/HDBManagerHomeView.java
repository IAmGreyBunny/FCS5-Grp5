package view.hdbmanager;

import session.Session;
import view.IMenuView;
import view.general.MyProfileView;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents the home view for HDB Manager.
 * It provides a menu with options for the manager to navigate to different functionalities such as:
 * - Viewing their profile
 * - Managing projects
 * - Managing applications
 * - Managing enquiries
 * The user interacts with the menu through input selection and based on the user's choice, the corresponding view is set.
 */
public class HDBManagerHomeView implements IMenuView {
    private int userInput;

    /**
     * Displays the main menu for the HDB Manager, offering options to view profile, manage projects, manage applications
     * or manage enquiries. Based on the user input the respective view is displayed.
     */
    @Override
    public void show() {
        System.out.println("Welcome!");
        System.out.println("1) My Profile");
        System.out.println("2) Manage Project");
        System.out.println("3) Manage Applications");
        System.out.println("4) Manage Enquiries");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    Session.getSession().setCurrentView(new MyProfileView());
                    break;
                case 2:
                    Session.getSession().setCurrentView(new ManagerProjectManagementView());
                    break;
                case 3:
                    Session.getSession().setCurrentView(new ManageApplicationView());
                    break;
                case 4:
                    Session.getSession().setCurrentView(new EnquiriesView());
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
