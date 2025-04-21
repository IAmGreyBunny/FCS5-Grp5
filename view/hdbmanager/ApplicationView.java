package view.hdbmanager;

import session.Session;
import view.hdbmanager.applications.*;
import view.MenuView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationView extends MenuView {
    private int userInput;

    @Override
    public void show() {
        System.out.println("==== Manage Applications ====");
        System.out.println("1. BTO Project");
        System.out.println("2. BTO Officers");
        System.out.println("3. Return");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    Session.getSession().setCurrentView(new BTOProjectApplicationView());
                    break;
                case 2:
                    Session.getSession().setCurrentView(new OfficerApplicationView());
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
