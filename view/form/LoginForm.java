package view.form;

import login.AuthController;
import session.Session;
import user.User;
import view.IFormView;
import view.HomeViewFactory;
import view.IView;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A form view that handles the process of logging in a user.
 * This class implements IFormView and interacts with AuthController to authenticate the user.
 */

public class LoginForm implements IFormView {

    /**
     * @param userInput userInput is a HashMap that contains user inputs for logging in.
     */
    HashMap<String,String> userInput = new HashMap<>();

    /**
     * This method prompts the user to input their username and password for logging in.
     * It validates the inputs using AuthController and updates the current view with HomeViewFactory.
     */
    @Override
    public void prompt() {
        String username;
        String password;

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("--- Login ---");

        /**
         * @param username username is the user's username for logging in
         * and puts the key-value pair into UserInput.
         */
        System.out.println("Enter Username: ");
        username = scanner.next();
        userInput.put("username",username);

        /**
         * @param password password is the user's password for logging in
         * and puts the key-value pair into UserInput.
         */
        System.out.println("Enter Password: ");
        password = scanner.next();
        userInput.put("password",password);

        /**
         * Logs in the user by authenticating their credentials and setting the current view.
         * @param userInput userInput is a HashMap that contains user inputs for logging in.
         */
        System.out.println("Logging in... ");
        if(AuthController.authenticate(this.getUserInput()))
        {
            User user = Session.getSession().getCurrentUser();
            IView view = HomeViewFactory.getHomeViewForUser(user);
            Session.getSession().setCurrentView(view);
        }
    }

    /**
     * Retrieves the user input map containing details for logging in.
     * @return userInput which is a HashMap with keys "username" and "password" representing the user's inputs.
     */
    @Override
    public HashMap<String,String> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }
}
