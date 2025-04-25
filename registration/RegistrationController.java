package registration;

import session.Session;
import user.User;
import user.UserRepository;
import user.UserRole;
import view.form.LoginForm;

import java.util.HashMap;

/**
 * Controller responsible for handling the registration of new users.
 * This class processes user input, validates it, and creates a new user account with the appropriate role.
 */
public class RegistrationController {

    HashMap<String, Object> userInput;

    /**
     * Constructor that accepts user input as parameter.
     * @param userInput HashMap containing the user provided data for registration.
     */
    public RegistrationController(HashMap<String, Object> userInput)
    {
        this.userInput = userInput;
    }

    /**
     * Registers a new user by creating their information and login data.
     * This method validates input, generates a new user ID, and assigns the user the role.
     * Once registration is successful, the current view is switched to the login form.
     * If validation fails an error message is displayed.
     */
    public void register()
    {
        int maxId = UserRepository.findMaxId();
        boolean validateInput = true; //Placeholder for method to validate input

        if(validateInput)
        {
            User user = new User(maxId+1, (String) userInput.get("name"), (int) userInput.get("age"), (boolean) userInput.get("maritalStatus"));
            UserRepository.createUserInfo(user);
            UserRepository.createUserLogin(user, (String) userInput.get("nric"), (String) userInput.get("password"));
            UserRepository.setUserRole(user.getUid(), UserRole.APPLICANT);
            System.out.println("Successfully Registered User");
            Session.getSession().setCurrentView(new LoginForm());
        }else {
            System.out.println("Error creating user");
        }
    }
}
