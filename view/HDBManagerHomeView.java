package view;

import session.Session;
import view.HDBManager.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HDBManagerHomeView extends HomeView {
    private int userInput;


    @Override
    public void show() {
        System.out.println("Welcome!");
        System.out.println("1) Manage Project");
        System.out.println("2) Manage Applications");
        System.out.println("3) Manage Enquiries");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    Session.getSession().setCurrentView(new ProjectView());
                    break;
                case 2:
                    Session.getSession().setCurrentView(new ApplicationView());
                    break;
                case 3:
                    Session.getSession().setCurrentView(new EnquiriesView());
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
