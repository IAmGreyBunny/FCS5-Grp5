package view.HDBManager;

import session.Session;
import view.HDBManagerHomeView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EnquiriesView extends HDBManagerHomeView {
    private int userInput;

    @Override
    public void show() {
        System.out.println("====== Manage Enquiries ======");
        System.out.println("1. View All Enquiries");
        System.out.println("2. Reply to Enquiries");
        System.out.println("3. Return");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to view all enquiries
                    break;
                case 2:
                    // code to answer enquiry
                    break;
                case 3:
                    Session.getSession().setCurrentView(new HDBManagerHomeView());
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
