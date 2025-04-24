package view.general;

import session.Session;
import view.MenuView;
import view.form.LoginForm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StartUpView extends MenuView {

    private int userInput;

    @Override
    public void show() {
        System.out.println("Welcome to BTO Management System");
        System.out.println("1) Login");
        System.out.println("2) Register");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch(userInput){
                case 1:
                    Session.getSession().setCurrentView(new LoginForm());
                    break;
                case 2:
                    Session.getSession().setCurrentView(new RegistrationView());
                    break;
                default:
                    System.out.println("Invalid Input");

            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
}
