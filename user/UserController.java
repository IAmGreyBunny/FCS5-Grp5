package user;

import session.Session;
import view.form.LoginForm;

import java.util.HashMap;

/**
 * This class provides functionalities to manage operations such as changing passwords and logging out.
 * It interacts with the UserRepository class to update user information and the Session class to manage user sessions.
 */
public class UserController {

    /**
     * Changes the password of the current logged in user.
     * This method updates the user's login credentials in the UserRepository and logs the user out after the change.
     * @param userInput A map containing the new username and password.
     */
    public static void changePassword(HashMap<String,String> userInput)
    {
        UserRepository.updateUserLogin(Session.getSession().getCurrentUser().getUid(),userInput.get("username"),userInput.get("password"));
        logoutCurrentUser();
    }

    /**
     * Logs out the current user by setting the current user to null and redirecting to the login screen.
     */
    public static void logoutCurrentUser()
    {
        Session.getSession().setCurrentUser(null); //Logs user out
        Session.getSession().setCurrentView(new LoginForm());
    }
}
