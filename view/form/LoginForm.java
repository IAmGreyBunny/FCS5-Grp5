package view.form;

import login.AuthController;
import session.Session;
import user.User;
import user.applicant.Applicant;
import user.hdbofficer.HDBOfficer;
import view.FormView;
import view.HomeViewFactory;
import view.View;
import view.general.DefaultHomeView;
import view.hdbmanager.HDBManagerHomeView;
import view.hdbofficer.HDBOfficerHomeView;

import java.util.HashMap;
import java.util.Scanner;

public class LoginForm extends FormView {

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
        if(AuthController.authenticate(this.getUserInput()))
        {
            User user = Session.getSession().getCurrentUser();
            View view = HomeViewFactory.getHomeViewForUser(user);
            Session.getSession().setCurrentView(view);
        }
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
