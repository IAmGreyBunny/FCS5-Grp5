package view.general;

import login.AuthController;
import session.Session;
import user.UserController;
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
        System.out.println("2) Logout");

        userInput = scanner.nextInt();

        switch (userInput){
            case 1:
                Session.getSession().setCurrentView(new ChangePasswordForm());
                break;
            case 2:
                UserController.logoutCurrentUser();
                break;
            default:
                System.out.println("Invalid Option");

        }
    }
}
