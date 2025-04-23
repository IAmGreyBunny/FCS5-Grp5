package view.hdbmanager.applications;

import session.Session;
import view.hdbmanager.HDBManagerHomeView;
import view.hdbmanager.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageProjectApplicationView extends ManageApplicationView {
    private int userInput;

    @Override
    public void show() {
        System.out.println("==== BTO Project Applications ====");
        System.out.println("1. Manage Requests");
        System.out.println("2. Manage Withdraws");
        System.out.println("3. Generate Report");
        System.out.println("4. Return");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to approve and reject requests
                    break;
                case 2:
                    // code to approve or reject withdraws
                    break;
                case 3:
                    // code to generate report
                    break;
                case 4:
                    Session.getSession().setCurrentView(new HDBManagerHomeView());
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
