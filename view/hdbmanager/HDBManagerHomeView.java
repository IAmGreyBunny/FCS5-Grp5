package view.hdbmanager;

import session.Session;
import view.MenuView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HDBManagerHomeView extends MenuView {
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
                    Session.getSession().setCurrentView(new ManagerProjectManagementView());
                    break;
                case 2:
                    Session.getSession().setCurrentView(new ManageApplicationView());
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
