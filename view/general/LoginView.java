package view.general;

import login.AuthController;
import view.FormView;

import java.util.HashMap;
import java.util.Scanner;

public class LoginView extends FormView {

    HashMap<String,String> userInput = new HashMap<>();

    @Override
    public void prompt() {
        String username;
        String password;

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("--- Login ---");
        System.out.println("Enter Username: ");
        username = scanner.next();
        userInput.put("username",username);
        System.out.println("Enter Password: ");
        password = scanner.next();
        userInput.put("password",password);

        System.out.println("Logging in... ");
        AuthController.authenticate(this.getUserInput());
    }

    @Override
    public HashMap<String,String> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }
}
