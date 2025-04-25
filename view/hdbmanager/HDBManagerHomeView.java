package view.hdbmanager;

import session.Session;
import view.IMenuView;
import view.general.MyProfileView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HDBManagerHomeView implements IMenuView {
    private int userInput;


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
