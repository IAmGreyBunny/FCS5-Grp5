package view.general;

import login.AuthController;
import session.Session;
import user.applicant.Applicant;
import user.hdbofficer.HDBOfficer;
import view.FormView;
import view.hdbmanager.HDBManagerHomeView;
import view.hdbofficer.HDBOfficerHomeView;

import java.util.HashMap;
import java.util.Scanner;

public class UpdateUserLoginView extends FormView {

    HashMap<String,String> userInput = new HashMap<>();

    @Override
    public void prompt() {
        String username;
        String password;

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Change Login Info ---");
        System.out.println("Leave blank for old values");
        System.out.println("Enter Username: ");
        username = scanner.nextLine();
        userInput.put("username",username);
        System.out.println("Enter Password: ");
        password = scanner.nextLine();
        userInput.put("password",password);


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
