package view.general;

import session.Session;
import user.UserController;
import view.HomeViewFactory;
import view.IMenuView;
import view.form.ChangePasswordForm;

import java.util.Scanner;

/**
 * A menu view that displays the user's profile options.
 * This class implements IMenuView and provides options for the user to change password, logout, or return to the home view.
 */

public class MyProfileView implements IMenuView {
    /**
     * @param userInput userInput is the user's input for selecting an option from the profile menu.
     */
    int userInput;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    /**
     * This method displays the profile menu and prompts the user to select an option.
     * It handles the user's input and navigates to the corresponding view based on the selected option.
     */
    @Override
    public void show() {
        System.out.println("--- My Profile ---");
        System.out.println("1) Change Password");
        System.out.println("2) Logout");
        System.out.println("3) Return");

        userInput = scanner.nextInt();

        /**
         * @param userInput userInput is the user's input for selecting an option from the profile menu.
         */
        switch (userInput){
            case 1:
                Session.getSession().setCurrentView(new ChangePasswordForm());
                break;
            case 2:
                UserController.logoutCurrentUser();
                break;
            case 3:
                // Returns Home
                Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
                break;
            default:
                System.out.println("Invalid Option");

        }
    }
}
