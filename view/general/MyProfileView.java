package view.general;

import session.Session;
import user.UserController;
import view.HomeViewFactory;
import view.IMenuView;
import view.form.ChangePasswordForm;

import java.util.Scanner;

public class MyProfileView implements IMenuView {

    int userInput;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Override
    public void show() {
        System.out.println("--- My Profile ---");
        System.out.println("1) Change Password");
        System.out.println("2) Logout");
        System.out.println("3) Return");

        userInput = scanner.nextInt();

        switch (userInput){
            case 1:
                Session.getSession().setCurrentView(new ChangePasswordForm());
                break;
            case 2:
                UserController.logoutCurrentUser();
                break;
            case 3:
                Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
                break;
            default:
                System.out.println("Invalid Option");

        }
    }
}
