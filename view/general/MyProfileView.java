package view.general;

import session.Session;
import view.MenuView;
import view.form.ChangePasswordForm;

import java.util.Scanner;

public class MyProfileView extends MenuView {

    int userInput;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Override
    public void show() {
        System.out.println("--- My Profile ---");
        System.out.println("1) Change Password");

        userInput = scanner.nextInt();

        switch (userInput){
            case 1:
                Session.getSession().setCurrentView(new ChangePasswordForm());
                break;
            default:
                System.out.println("Invalid Option");

        }
    }
}
