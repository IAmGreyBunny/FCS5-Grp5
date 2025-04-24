package view.form;

import login.AuthController;
import view.FormView;

import java.util.HashMap;
import java.util.Scanner;

public class ChangePasswordForm extends FormView {

    HashMap<String,String> userInput = new HashMap<>();

    @Override
    public void prompt() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Enter new password again: ");
        String newPasswordAgain = scanner.nextLine();

        if(newPassword.equals(newPasswordAgain))
        {
            System.out.println("Login to confirm");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            userInput.put("username",username);
            userInput.put("password",password);

            if(AuthController.authenticate(userInput))
            {
                //Change password
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

    @Override
    public <T> T getUserInput() {
        return null;
    }

    @Override
    public void show() {
        prompt();
    }
}
