package user;

import session.Session;
import view.general.LoginView;

import java.util.HashMap;

public class UserController {

    public static void changePassword(HashMap<String,String> userInput)
    {
        UserRepository.updateUserLogin(Session.getSession().getCurrentUser().getUid(),userInput.get("username"),userInput.get("password"));
        logoutCurrentUser();
    }

    public static void logoutCurrentUser()
    {
        Session.getSession().setCurrentUser(null); //Logs user out
        Session.getSession().setCurrentView(new LoginView());
    }
}
