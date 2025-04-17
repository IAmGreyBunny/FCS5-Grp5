package view.general;

import view.MenuView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BTOApplicationMenuView extends MenuView {
    private int userInput;


    @Override
    public void show() {
        System.out.println("Welcome!");
        System.out.println("1) Apply for BTO Project");
        System.out.println("2) View ALL projects");
        System.out.println("3) View Applied BTO Projects");
        System.out.println("4) Book Flat");
        System.out.println("5) Request Withdrawal");
        System.out.println("6) Submit Enquiry");
        System.out.println("7) View Enquiry");
        System.out.println("8) Edit Enquiry");
        System.out.println("9) Delete Enquiry");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to print BTO projects that can be applied for
                    // code to apply for BTO Project
                    break;
                case 2:
                    // code to view all projects
                    break;
                case 3:
                    // code to view projects that person has applied for
                    break;
                case 4:
                    // book flat (only when has a BTO that has been approved of)
                    break;
                case 5:
                    // request withdrawal (only when there is a BTO that has been approved of)
                    break;
                case 6:
                    // submit enquiry
                    break;
                case 7:
                    // view enquiry
                    break;
                case 8:
                    // edit enquiry (must have submitted an enquiry)
                    break;
                case 9:
                    // delete enquiry (must have submitted an enquiry)
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}
