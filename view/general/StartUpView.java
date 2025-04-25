package view.general;

import session.Session;
import view.IMenuView;
import view.form.LoginForm;
import view.form.RegistrationForm;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A menu view that displays the startup options for the user.
 * This class implements IMenuView and provides options for the user to login or register.
 */
public class StartUpView implements IMenuView {

    /**
     * @param userInput userInput is the user's input for selecting an option from the startup menu.
     */
    private int userInput;

    /**
     * This method displays the startup menu and prompts the user to select an option.
     * It handles the user's input and navigates to the corresponding view based on the selected option.
     */
    @Override
    public void show() {
        System.out.println("Welcome to BTO Management System");
        System.out.println("1) Login");
        System.out.println("2) Register");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            /**
             * @param userInput userInput is the user's input for selecting an option from the startup menu.
             */
            switch(userInput) {
                case 1:
                    Session.getSession().setCurrentView(new LoginForm());
                    break;
                case 2:
                    Session.getSession().setCurrentView(new RegistrationForm());
                    break;
                default:
                    System.out.println("Invalid Input");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
}


