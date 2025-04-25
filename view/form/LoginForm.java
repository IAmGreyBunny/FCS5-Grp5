package view.form;

import login.AuthController;
import session.Session;
import user.User;
import view.IFormView;
import view.HomeViewFactory;
import view.IView;

import java.util.HashMap;
import java.util.Scanner;

public class LoginForm implements IFormView {

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
            IView view = HomeViewFactory.getHomeViewForUser(user);
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
