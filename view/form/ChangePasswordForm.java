package view.form;

import login.AuthController;
import user.UserController;
import view.FormView;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A form view that handles the process of changing a user's password.
 * This class extends FormView and interacts with AuthController and UserController to validate and update the password.
 */

public class ChangePasswordForm extends FormView {

    /**
     * @param userInput userInput is a HashMap that contains "username":String and "password":String
     */
    HashMap<String,String> userInput = new HashMap<>();

    /**
     * This method prompts the user to input their new password and confirm it.
     * They are also requested to enter their username and old password for authentication.
     */
    @Override
    public void prompt() {

        Scanner scanner = new Scanner(System.in);
        /**
         * @param newPassword newPassword is the password that the user wants to set
         */
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        /**
         * @param newPasswordAgain newPasswordAgain is the confirmation of the new password
         */
        System.out.print("Enter new password again: ");
        String newPasswordAgain = scanner.nextLine();

        if(newPassword.equals(newPasswordAgain))
        {
            /**
             * @param username username is the user's current username
             * @param password password is the user's old password
             * Both are taken for authentication.
             * @return void
             *         Messages of success and failure is displayed depending on whether password change is successful.
             */
            System.out.println("Login to confirm");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            userInput.put("username",username);
            userInput.put("password",password);

            if(AuthController.authenticate(userInput))
            {
                userInput.put("password",newPassword);
                UserController.changePassword(userInput);
            }
            else
            {
                System.out.println("Login information wrong, password change failed!");
            }
        }
        else{
            System.out.println("Password did not match!");
        }
    }

    /**
     * Retrieves the user input map containing "username":String and "password":String
     * @return userInput which is a HashMap with "username":String and "password":String representing the user's inputs
     */
    @Override
    public HashMap<String, String> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }
}
