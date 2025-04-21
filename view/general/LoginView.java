package view.general;

import login.AuthController;
import session.Session;
import user.applicant.Applicant;
import user.hdbofficer.HDBOfficer;
import view.FormView;
import view.hdbmanager.ApplicationView;
import view.hdbmanager.HDBManagerHomeView;
import view.hdbmanager.applications.BTOProjectApplicationView;
import view.hdbofficer.HDBOfficerHomeView;

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
        if(AuthController.authenticate(this.getUserInput()))
        {
            if (Session.getSession().getCurrentUser() instanceof Applicant)
            {
                Session.getSession().setCurrentView(new DefaultHomeView());
            }
            else if (Session.getSession().getCurrentUser() instanceof HDBOfficer)
            {
                Session.getSession().setCurrentView(new HDBOfficerHomeView());
            }
            else {
                Session.getSession().setCurrentView(new HDBManagerHomeView());
            }
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
