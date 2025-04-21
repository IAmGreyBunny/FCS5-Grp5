package view.hdbmanager;

import session.Session;
import view.MenuView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProjectView extends MenuView {
    private int userInput;

    @Override
    public void show() {
        System.out.println("==== Manage BTO Project ====");
        System.out.println("1. Create New Project Listing");
        System.out.println("2. Edit Project Listing");
        System.out.println("3. Delete Project Listing");
        System.out.println("4. Toggle Visibility");
        System.out.println("5. View Project Listings");
        System.out.println("6. Return");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to create new listing (CreateListingForm)
                    break;
                case 2:
                    // code to edit existing listing
                    break;
                case 3:
                    // code to delete listing
                    break;
                case 4:
                    // code to toggle visibility of a project listing
                    break;
                case 5:
                    // code to view project listings (can filter to view all or those managed by self)
                    break;
                case 6:
                    Session.getSession().setCurrentView(new HDBManagerHomeView());
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
