package view.HDBManager;

import session.Session;
import view.HDBOfficerHomeView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OfficerProjectView extends HDBOfficerHomeView {
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
                    // code to print details of the project
                    break;
                case 2:
                    // code to view enquiries
                    break;
                case 3:
                    // flat selection responsibilies
                    break;
                case 4:
                    // generate flat selection receipt
                    break;
                case 5:
                    Session.getSession().setCurrentView(new HDBOfficerHomeView());
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
